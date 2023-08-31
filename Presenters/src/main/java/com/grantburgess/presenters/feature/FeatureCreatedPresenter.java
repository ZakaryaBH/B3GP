package com.grantburgess.presenters.feature;

import com.grantburgess.ports.presenters.feature.FeatureCreatedOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureCreatedViewModel;
import com.grantburgess.ports.usescases.feature.addfeature.NewFeatureResponse;

public class FeatureCreatedPresenter implements FeatureCreatedOutputBoundary {
    private FeatureCreatedViewModel viewModel;

    public FeatureCreatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(NewFeatureResponse responseModel) {
        viewModel = new FeatureCreatedViewModel(responseModel.getId().toString());
    }
}
