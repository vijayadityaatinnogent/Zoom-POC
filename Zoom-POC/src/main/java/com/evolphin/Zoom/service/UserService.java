package com.evolphin.Zoom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evolphin.Zoom.dto.projection.UserWithAssetsProjection;
import com.evolphin.Zoom.dto.request.UserRequestDto;
import com.evolphin.Zoom.dto.response.AssetResponseDto;
import com.evolphin.Zoom.dto.response.UserProfileDTO;
import com.evolphin.Zoom.mapper.AssetMapper;
import com.evolphin.Zoom.mapper.UserMapper;
import com.evolphin.Zoom.model.Asset;
import com.evolphin.Zoom.model.User;
import com.evolphin.Zoom.repository.AssetRepository;
import com.evolphin.Zoom.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private AssetMapper assetMapper;

    public List<User> getAllUsers() {
            return userRepository.findAll();
    }

    public User createUser(UserRequestDto userDto) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = UserMapper.toEntity(userDto);
        return userRepository.save(user);
    }

    public UserProfileDTO getUserWithAssets(String userId) {
        UserWithAssetsProjection userWithAssets = userRepository.findUserWithAssets(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<AssetResponseDto> assetDtos = userWithAssets.getAssets().stream()
                .map(AssetMapper::toResponseDto)
                .toList();

        User user = UserMapper.fromProjection(userWithAssets);
        return UserMapper.toUserProfileDTO(user, assetDtos);
    }

    public UserProfileDTO getUserLatestAssets(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Asset> latestAssets = assetRepository.findByOwnerAndIsLatestTrue(user);
        List<AssetResponseDto> assetDtos = latestAssets.stream()
                .map(AssetMapper::toResponseDto)
                .toList();

        return UserMapper.toUserProfileDTO(user, assetDtos);
    }
}
