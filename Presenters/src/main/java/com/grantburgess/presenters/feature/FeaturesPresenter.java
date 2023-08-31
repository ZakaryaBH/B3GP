package com.grantburgess.presenters.feature;

import com.grantburgess.ports.presenters.feature.FeaturesOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeaturesViewModel;
import com.grantburgess.ports.usescases.feature.get.features.FeaturesResponse;

public class FeaturesPresenter extends BaseFeaturePresenter implements FeaturesOutputBoundary {
    private FeaturesViewModel viewModel;

    public FeaturesViewModel getViewModel() {
        return viewModel;
    }

    public void present(FeaturesResponse responseModel) {
        FeaturesViewModel.FeaturesViewModelBuilder offersViewModelBuilder = FeaturesViewModel.builder();
        responseModel.getFeatures()
                .stream()
                .map(BaseFeaturePresenter::mapToFeatureViewModel)
                .forEach(offersViewModelBuilder::FeaturesViewModel);
        viewModel = offersViewModelBuilder.build();
    }
}