package com.evolphin.Zoom.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import lombok.*;

@Document(collection = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    
    @NotBlank(message = "username is required")
    private String username;
    
    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private String email;
    
    @NotBlank(message = "full name is required")
    private String fullName;
    
    private String department;
    
    private String role = "USER";
    
    private Boolean isActive = true;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}