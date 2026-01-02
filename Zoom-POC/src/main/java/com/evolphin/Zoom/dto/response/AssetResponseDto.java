package com.evolphin.Zoom.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponseDto {
    private String id;
    private String name;
    private Integer version;
    private Boolean isLatest;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String extension;
    private String status;
    private List<String> tags;
    private String ownerId;
    private String type;
    private String assetId;
    private Long size;
    private String downloadUrl;
}