package com.grantburgess.presenters.feature;

import com.grantburgess.ports.presenters.feature.FeatureUpdatedOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureUpdatedViewModel;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdateFeatureResponse;

public class FeatureUpdatedPresenter implements FeatureUpdatedOutputBoundary {
    private FeatureUpdatedViewModel viewModel;

    public FeatureUpdatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(UpdateFeatureResponse responseModel) {
        viewModel = new FeatureUpdatedViewModel(responseModel.getId().toString());
    }
}
