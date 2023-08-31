package com.grantburgess.ports.usescases.feature.updateFeature;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateFeatureResponse {
    private UUID id;

    public UpdateFeatureResponse(UUID id) {
        this.id = id;
    }
}
