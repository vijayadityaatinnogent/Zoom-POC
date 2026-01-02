package com.evolphin.Zoom.mapper;

import com.evolphin.Zoom.model.Asset;
import com.evolphin.Zoom.dto.request.AssetRequestDto;
import com.evolphin.Zoom.dto.response.AssetResponseDto;
import com.evolphin.Zoom.model.User;
import com.evolphin.Zoom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssetMapper {
    
    @Autowired
    private UserRepository userRepository;

    // Request DTO -> Entity
    public Asset toEntity(AssetRequestDto requestDto) {
        // Find User by ownerId
        User owner = userRepository.findById(requestDto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        
        return Asset.builder()
                .assetId(requestDto.getAssetId())
                .name(requestDto.getName())
                .type(requestDto.getType())
                .size(requestDto.getSize())
                .downloadUrl(requestDto.getDownloadUrl())
                .version(requestDto.getVersion() != null ? requestDto.getVersion() : 1)
                .isLatest(requestDto.getIsLatest())
                .createdBy(requestDto.getCreatedBy())
                .createdAt(requestDto.getCreatedAt() != null ? requestDto.getCreatedAt() : LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .extension(requestDto.getExtension())
                .status(requestDto.getStatus() != null ? requestDto.getStatus() : "UPLOADED")
                .tags(requestDto.getTags())
                .owner(owner) // Set User object
                .build();
    }

    // Entity -> Response DTO
    public static AssetResponseDto toResponseDto(Asset asset) {
        return AssetResponseDto.builder()
                .id(asset.getId())
                .assetId(asset.getAssetId())
                .name(asset.getName())
                .type(asset.getType())
                .size(asset.getSize())
                .downloadUrl(asset.getDownloadUrl())
                .version(asset.getVersion())
                .isLatest(asset.getIsLatest())
                .createdBy(asset.getCreatedBy())
                .createdAt(asset.getCreatedAt())
                .updatedAt(asset.getUpdatedAt())
                .extension(asset.getExtension())
                .status(asset.getStatus())
                .tags(asset.getTags())
                .ownerId(asset.getOwner() != null ? asset.getOwner().getId() : null)
                .build();
    }
}
