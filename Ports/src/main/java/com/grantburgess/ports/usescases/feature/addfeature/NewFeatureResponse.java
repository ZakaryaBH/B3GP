package com.grantburgess.ports.usescases.feature.addfeature;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewFeatureResponse {
    private UUID id;

    public NewFeatureResponse(UUID id) {
        this.id = id;
    }
}
