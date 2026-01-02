package com.evolphin.Zoom.repository;

import com.evolphin.Zoom.dto.projection.UserWithAssetsProjection;
import com.evolphin.Zoom.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    @Aggregation(pipeline = {
        "{ $match: { '_id': ?0 } }",
        "{ $lookup: { from: 'Assets', localField: '_id', foreignField: 'owner.$id', as: 'assets' } }"
    })
    Optional<UserWithAssetsProjection> findUserWithAssets(String userId);
}