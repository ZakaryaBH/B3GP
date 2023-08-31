package com.grantburgess.database.jpa;

import com.grantburgess.database.jpa.data.FeatureData;
import com.grantburgess.database.jpa.repositories.FeatureRepository;
import com.grantburgess.entities.Feature;
import com.grantburgess.ports.database.FeatureGateway;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JpaFeatureGateway implements FeatureGateway {
    private final FeatureRepository featureRepository;

    public Collection<Feature> getAllExcludingCancelled() {
        return featureRepository.findAll()
                .stream()
                .map(this::mapToFeature)
                .collect(Collectors.toList());
    }

    public JpaFeatureGateway(final FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    private Feature mapToFeature(FeatureData featureData) {
        return new Feature(
                featureData.getId(),
                featureData.getName(),
                featureData.getDescription(),
                featureData.getStartDate(),
                featureData.getEndDate(),
                featureData.getIdLot()
        );
    }

    public UUID add(Feature offer) {
        UUID id = UUID.randomUUID();
        FeatureData featureData = FeatureData
                .builder()
                .id(id)
                .name(offer.getName())
                .description(offer.getDescription())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .idLot(offer.getIdLot())
                .build();
        featureRepository.save(featureData);

        return id;
    }

    public Feature getByIdExcludingCancelled(UUID id) {
        return featureRepository.findById(id)
                .map(this::mapToFeature)
                .orElse(null);
    }

    public UUID update(Feature offer) {
        FeatureData featureData = FeatureData
                .builder()
                .id(offer.getId())
                .name(offer.getName())
                .description(offer.getDescription())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .idLot(offer.getIdLot())
                .build();

        featureRepository.save(featureData);
        return offer.getId();
    }

    public UUID remove(UUID id) {
        featureRepository.deleteById(id);
        return id;
    }

    public boolean existsByName(String name) {
        return featureRepository.existsByName(name);
    }
}
