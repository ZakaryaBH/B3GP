package com.grantburgess.ports.presenters.feature;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FeaturesViewModel {
    @Singular("FeaturesViewModel") private List<FeatureViewModel> features;
}
