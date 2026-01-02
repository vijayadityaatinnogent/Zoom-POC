package com.evolphin.Zoom.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetRequestDto {
    @NotBlank(message = "name is required")
    public String name;
    
    @Min(value = 1, message = "version must be at least 1")
    public Integer version;
    
    public Boolean isLatest;
    
    @NotBlank(message = "createdBy is required")
    public String createdBy;

    public String ownerName;
    
    public LocalDateTime createdAt;
    
    public LocalDateTime updatedAt;
    
    @Pattern(regexp = "^\\.[a-zA-Z0-9]+$", message = "extension must start with dot (e.g., .jpg, .pdf)")
    public String extension;
    
    @Pattern(regexp = "^(UPLOADED|PROCESSING|COMPLETED|FAILED)$", message = "status must be UPLOADED, PROCESSING, COMPLETED, or FAILED")
    public String status;
    
    @Size(max = 10, message = "maximum 10 tags allowed")
    public List<@NotBlank(message = "tag cannot be empty") String> tags;
    
    public String ownerId; // Keep for API input
    
    @NotBlank(message = "type is required")
    public String type;
    @NotBlank(message = "asset id is required")
    public String assetId;
    @NotNull(message = "size is required")
    @Positive(message = "size must be greater than 0")
    public Long size;

    @NotBlank(message = "download URL is required")
    public String downloadUrl;
}
