package com.evolphin.Zoom.controller;

import com.evolphin.Zoom.dto.request.AssetRequestDto;
import com.evolphin.Zoom.dto.response.AssetResponseDto;
import com.evolphin.Zoom.mapper.AssetMapper;
import com.evolphin.Zoom.model.Asset;
import com.evolphin.Zoom.service.AssetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;
    
    @Autowired
    private AssetMapper assetMapper;

    @PostMapping("/zoom/save-assets")
    public String createAssets(@Valid @RequestBody AssetRequestDto assetDto) {
        Asset asset = assetMapper.toEntity(assetDto);
        assetService.saveAssets(asset);
        return "Asset synced successfully";
    }

    @PostMapping("/zoom/save-assets-bulk")
    public String createAssetsBulk(@Valid @RequestBody List<AssetRequestDto> assetDtos) {
        assetService.saveAssetBulk(assetDtos);
        return "Multiple Assets synced successfully";
    }

    @GetMapping("/assets")
    public List<AssetResponseDto> getAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/assets/latest")
    public List<AssetResponseDto> getLatestAssets() {
        return assetService.getAllLatestAssets();
    }

    @GetMapping("/assets/{assetId}/latest")
    public AssetResponseDto getLatestAsset(@PathVariable String assetId) {
        return assetService.getLatestAsset(assetId)
                .orElseThrow(() -> new RuntimeException("Latest asset not found"));
    }

    @GetMapping("/assets/{assetId}/versions")
    public List<AssetResponseDto> getAllVersions(@PathVariable String assetId) {
        return assetService.getAllVersions(assetId);
    }

    @PutMapping("/assets/{assetId}")
    public AssetResponseDto updateAsset(@PathVariable String assetId,
            @Valid @RequestBody AssetRequestDto assetDto) {
        return assetService.updateAsset(assetId, assetDto);
    }

    @DeleteMapping("/assets/{assetId}")
    public String deleteAsset(@PathVariable String assetId) {
        return assetService.deleteAsset(assetId);
    }

}
