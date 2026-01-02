package com.evolphin.Zoom.repository;

import com.evolphin.Zoom.model.Asset;
import com.evolphin.Zoom.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository extends MongoRepository<Asset, String> {
    Optional<Asset> findByAssetId(String assetId);
    
    Optional<Asset> findByAssetIdAndIsLatestTrue(String assetId);
    
    List<Asset> findByAssetIdOrderByVersionDesc(String assetId);
    
    List<Asset> findByIsLatestTrue();
    
    List<Asset> findByOwner(User owner);
    
    List<Asset> findByOwnerAndIsLatestTrue(User owner);

    void deleteByAssetId(String assetId);

    Optional<Asset> findByAssetIdAndIsLatest(String assetId);
}
