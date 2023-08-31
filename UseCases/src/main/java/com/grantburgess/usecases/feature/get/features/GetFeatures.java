package com.grantburgess.usecases.feature.get.features;

import com.grantburgess.ports.database.FeatureGateway;
import com.grantburgess.ports.presenters.feature.FeaturesOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.get.features.GetFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.get.features.GetFeaturesRequest;
import com.grantburgess.ports.usescases.feature.get.features.FeaturesResponse;
import com.grantburgess.usecases.feature.get.GetFeatureBase;

public class GetFeatures extends GetFeatureBase implements GetFeatureInputBoundary {
    private final FeaturesOutputBoundary presenter;
    private final FeatureGateway featureGateway;
    private final Clock clock;

    public GetFeatures(FeaturesOutputBoundary presenter, FeatureGateway featureGateway, Clock clock) {
        this.presenter = presenter;
        this.featureGateway = featureGateway;
        this.clock = clock;
    }

    public void execute(GetFeaturesRequest request) {
        FeaturesResponse.FeaturesResponseBuilder result = FeaturesResponse.builder();
        featureGateway.getAllExcludingCancelled().forEach(offer -> result.addFeature(makeFeatureResponse(offer, clock)));
        presenter.present(result.build());
    }
}
