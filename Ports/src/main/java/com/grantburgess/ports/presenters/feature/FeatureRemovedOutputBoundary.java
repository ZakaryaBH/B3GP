package com.grantburgess.ports.presenters.feature;

import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureResponse;

public interface FeatureRemovedOutputBoundary {
    FeatureRemovedViewModel getViewModel();
    void present(RemoveFeatureResponse responseModel);
}
