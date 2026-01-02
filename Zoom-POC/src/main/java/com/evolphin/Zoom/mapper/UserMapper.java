package com.evolphin.Zoom.mapper;

import com.evolphin.Zoom.dto.projection.UserWithAssetsProjection;
import com.evolphin.Zoom.dto.request.UserRequestDto;
import com.evolphin.Zoom.dto.response.AssetResponseDto;
import com.evolphin.Zoom.dto.response.UserProfileDTO;
import com.evolphin.Zoom.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserMapper {
    
    // Request DTO -> Entity
    public static User toEntity(UserRequestDto requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .fullName(requestDto.getFullName())
                .department(requestDto.getDepartment())
                .role(requestDto.getRole() != null ? requestDto.getRole() : "USER")
                .isActive(requestDto.getIsActive() != null ? requestDto.getIsActive() : true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    
    // Projection -> Entity
    public static User fromProjection(UserWithAssetsProjection projection) {
        return User.builder()
                .id(projection.getId())
                .username(projection.getUsername())
                .email(projection.getEmail())
                .fullName(projection.getFullName())
                .department(projection.getDepartment())
                .role(projection.getRole())
                .isActive(projection.getIsActive())
                .createdAt(projection.getCreatedAt())
                .updatedAt(projection.getUpdatedAt())
                .build();
    }
    
    // Entity -> UserProfileDTO (with assets)
    public static UserProfileDTO toUserProfileDTO(User user, List<AssetResponseDto> assets) {
        return UserProfileDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .department(user.getDepartment())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .assets(assets)
                .totalAssets(assets.size())
                .build();
    }
}