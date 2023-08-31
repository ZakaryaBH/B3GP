package com.grantburgess.ports.usescases.feature.get.features;

import com.grantburgess.ports.usescases.feature.get.FeatureResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FeaturesResponse {
    @Singular("addFeature") private List<FeatureResponse> Features;
}
