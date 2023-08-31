package com.grantburgess.ports.presenters.feature;

import com.grantburgess.ports.usescases.feature.addfeature.NewFeatureResponse;

public interface FeatureCreatedOutputBoundary {
    FeatureCreatedViewModel getViewModel();
    void present(NewFeatureResponse responseModel);
}
