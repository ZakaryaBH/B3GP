package com.grantburgess.ports.usescases.feature.removeFeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RemoveFeatureRequest {
    private UUID id;
}
