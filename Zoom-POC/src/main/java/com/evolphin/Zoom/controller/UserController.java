package com.evolphin.Zoom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.evolphin.Zoom.dto.request.UserRequestDto;
import com.evolphin.Zoom.dto.response.UserProfileDTO;
import com.evolphin.Zoom.model.User;
import com.evolphin.Zoom.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    public String createUser(@Valid @RequestBody UserRequestDto userDto) {
        userService.createUser(userDto);
        return "User created successfully";
    }
    
    @GetMapping("/{userId}/profile")
    public UserProfileDTO getUserProfile(@PathVariable String userId) {
        return userService.getUserWithAssets(userId);
    }
    
    @GetMapping("/{userId}/assets/latest")
    public UserProfileDTO getUserLatestAssets(@PathVariable String userId) {
        return userService.getUserLatestAssets(userId);
    }
}