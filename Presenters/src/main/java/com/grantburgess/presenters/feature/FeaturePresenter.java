package com.grantburgess.presenters.feature;

import com.grantburgess.ports.presenters.feature.FeatureOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureViewModel;
import com.grantburgess.ports.usescases.feature.get.FeatureResponse;

public class FeaturePresenter extends BaseFeaturePresenter implements FeatureOutputBoundary {
    private FeatureViewModel viewModel;

    public FeatureViewModel getViewModel() {
        return viewModel;
    }

    public void present(FeatureResponse responseModel) {
        viewModel = mapToFeatureViewModel(responseModel);
    }
}
