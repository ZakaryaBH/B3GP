package com.grantburgess.ports.usescases.feature.get.featurebyid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetFeatureRequest {
    private UUID id;
}
