package com.grantburgess.presenters.feature;

import com.grantburgess.ports.presenters.feature.FeatureRemovedOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureRemovedViewModel;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureResponse;

public class FeatureRemovedPresenter implements FeatureRemovedOutputBoundary {
    private FeatureRemovedViewModel viewModel;

    public FeatureRemovedViewModel getViewModel() {
        return viewModel;
    }

    public void present(RemoveFeatureResponse responseModel) {
        viewModel = new FeatureRemovedViewModel(responseModel.getId().toString());
    }
}
