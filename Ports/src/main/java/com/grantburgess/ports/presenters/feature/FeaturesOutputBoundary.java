package com.grantburgess.ports.presenters.feature;

import com.grantburgess.ports.usescases.feature.get.features.FeaturesResponse;

public interface FeaturesOutputBoundary {
    FeaturesViewModel getViewModel();
    void present(FeaturesResponse responseModel);
}
