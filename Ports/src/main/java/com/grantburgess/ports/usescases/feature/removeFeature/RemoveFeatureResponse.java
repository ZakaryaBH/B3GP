package com.grantburgess.ports.usescases.feature.removeFeature;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RemoveFeatureResponse {
    private UUID id;
    public RemoveFeatureResponse(UUID id) {
        this.id = id;
    }
}
