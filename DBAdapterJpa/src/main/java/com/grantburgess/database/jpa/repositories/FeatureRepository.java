package com.grantburgess.database.jpa.repositories;

import com.grantburgess.database.jpa.data.FeatureData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeatureRepository extends CrudRepository<FeatureData, UUID> {
    List<FeatureData> findAll();
    Optional<FeatureData> findById(UUID id);
    boolean existsByName(String name);
}
