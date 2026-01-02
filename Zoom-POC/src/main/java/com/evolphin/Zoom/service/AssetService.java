package com.evolphin.Zoom.service;

import com.evolphin.Zoom.dto.request.AssetRequestDto;
import com.evolphin.Zoom.dto.response.AssetResponseDto;
import com.evolphin.Zoom.mapper.AssetMapper;
import com.evolphin.Zoom.model.Asset;
import com.evolphin.Zoom.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    
    @Autowired
    private AssetMapper assetMapper;

    public Asset saveAssets(Asset asset) {
        // Handle version logic
        handleVersioning(asset);
        return assetRepository.save(asset);
    }

    public List<Asset> saveAssetBulk(List<AssetRequestDto> dtos) {
        List<Asset> assets = dtos.stream()
                .map(assetMapper::toEntity)
                .peek(this::handleVersioning)
                .toList();
        return assetRepository.saveAll(assets);
    }

    public List<AssetResponseDto> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(AssetMapper::toResponseDto)
                .toList();
    }

    public List<AssetResponseDto> getAllLatestAssets() {
        return assetRepository.findByIsLatestTrue().stream()
                .map(AssetMapper::toResponseDto)
                .toList();
    }

    public Optional<AssetResponseDto> getLatestAsset(String assetId) {
        return assetRepository.findByAssetIdAndIsLatestTrue(assetId)
                .map(AssetMapper::toResponseDto);
    }

    public List<AssetResponseDto> getAllVersions(String assetId) {
        return assetRepository.findByAssetIdOrderByVersionDesc(assetId).stream()
                .map(AssetMapper::toResponseDto)
                .toList();
    }

    public AssetResponseDto updateAsset(String assetId, AssetRequestDto updatedDto) {
        Asset existing = assetRepository.findByAssetIdAndIsLatestTrue(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        
        // Update all fields
        existing.setName(updatedDto.getName());
        existing.setType(updatedDto.getType());
        existing.setSize(updatedDto.getSize());
        existing.setDownloadUrl(updatedDto.getDownloadUrl());
        existing.setCreatedBy(updatedDto.getCreatedBy());
        existing.setExtension(updatedDto.getExtension());
        existing.setStatus(updatedDto.getStatus());
        existing.setTags(updatedDto.getTags());
        existing.setUpdatedAt(LocalDateTime.now());

        Asset saved = assetRepository.save(existing);
        return AssetMapper.toResponseDto(saved);
    }

    public String deleteAsset(String assetId) {
        assetRepository.deleteByAssetId(assetId);
        return "Asset deleted successfully";
    }

    private void handleVersioning(Asset asset) {
        // Find existing assets with same assetId
        List<Asset> existingVersions = assetRepository.findByAssetIdOrderByVersionDesc(asset.getAssetId());
        
        if (!existingVersions.isEmpty()) {
            // Set version to latest + 1
            int latestVersion = existingVersions.get(0).getVersion();
            asset.setVersion(latestVersion + 1);
            
            // Mark all previous versions as not latest
            existingVersions.forEach(existing -> {
                existing.setIsLatest(false);
                existing.setUpdatedAt(LocalDateTime.now());
            });
            assetRepository.saveAll(existingVersions);
        } else {
            // First version
            asset.setVersion(1);
        }
        
        // Mark current as latest
        asset.setIsLatest(true);
        asset.setCreatedAt(LocalDateTime.now());
        asset.setUpdatedAt(LocalDateTime.now());
    }
}
