package com.evolphin.Zoom.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "username is required")
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
    private String username;
    
    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private String email;
    
    @NotBlank(message = "full name is required")
    @Size(max = 100, message = "full name cannot exceed 100 characters")
    private String fullName;
    
    @Size(max = 50, message = "department cannot exceed 50 characters")
    private String department;
    
    @Pattern(regexp = "^(USER|ADMIN|MANAGER)$", message = "role must be USER, ADMIN, or MANAGER")
    private String role;
    
    private Boolean isActive;
}