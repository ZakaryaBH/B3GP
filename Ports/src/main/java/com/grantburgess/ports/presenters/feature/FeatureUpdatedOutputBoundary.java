package com.grantburgess.ports.presenters.feature;

import com.grantburgess.ports.usescases.feature.updateFeature.UpdateFeatureResponse;

public interface FeatureUpdatedOutputBoundary {
    FeatureUpdatedViewModel getViewModel();
    void present(UpdateFeatureResponse responseModel);
}
