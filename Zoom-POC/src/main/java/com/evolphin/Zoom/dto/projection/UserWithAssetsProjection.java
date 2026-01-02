package com.evolphin.Zoom.dto.projection;

import java.time.LocalDateTime;
import java.util.List;

import com.evolphin.Zoom.model.Asset;

import lombok.Data;

@Data
public class UserWithAssetsProjection {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String department;
    private String role;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Asset> assets;
}