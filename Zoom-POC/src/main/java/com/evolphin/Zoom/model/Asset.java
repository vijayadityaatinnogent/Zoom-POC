package com.evolphin.Zoom.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import lombok.*;

@Document(collection = "Assets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    @Id
    private String id;
    
    @NotBlank(message = "name is required")
    private String name;

    @Min(value = 1, message = "version must be at least 1")
    private Integer version = 1;

    private Boolean isLatest;

    @NotBlank(message = "createdBy is required")
    private String createdBy;
    
    @DBRef
    private User owner; // DBRef to User document

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Pattern(regexp = "^\\.[a-zA-Z0-9]+$", message = "extension must start with dot (e.g., .jpg, .pdf)")
    private String extension;

    @Pattern(regexp = "^(UPLOADED|PROCESSING|COMPLETED|FAILED)$", message = "status must be UPLOADED, PROCESSING, COMPLETED, or FAILED")
    private String status = "UPLOADED";

    @Size(max = 10, message = "maximum 10 tags allowed")
    private List<@NotBlank(message = "tag cannot be empty") String> tags;

    @NotBlank(message = "type is required")
    private String type;
    @NotBlank(message = "asset id is required")
    private String assetId;
    @NotNull(message = "size is required")
    @Positive(message = "size must be greater than 0")
    private Long size;
    @NotBlank(message = "download URL is required")
    private String downloadUrl;

    
}
