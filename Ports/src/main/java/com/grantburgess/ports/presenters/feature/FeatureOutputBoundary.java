package com.grantburgess.ports.presenters.feature;

import com.grantburgess.ports.usescases.feature.get.FeatureResponse;

public interface FeatureOutputBoundary {
    FeatureViewModel getViewModel();
    void present(FeatureResponse responseModel);
}
