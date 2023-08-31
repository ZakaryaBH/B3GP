package com.grantburgess.usecases.feature.removefeature;

import com.grantburgess.ports.database.FeatureGateway;
import com.grantburgess.ports.presenters.feature.FeatureRemovedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureRequest;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureResponse;

import java.util.UUID;

public class RemoveFeature implements RemoveFeatureInputBoundary {
    private final FeatureRemovedOutputBoundary presenter;
    private final FeatureGateway featureGateway;
    private final Clock clock;

    public RemoveFeature(FeatureRemovedOutputBoundary presenter, FeatureGateway featureGateway, Clock clock) {
        this.presenter = presenter;
        this.featureGateway = featureGateway;
        this.clock = clock;
    }

    public void execute(RemoveFeatureRequest request) {
        UUID id = featureGateway.remove(request.getId());
        if (id == null)
            throw new FeatureGateway.OfferNotFoundException();
        RemoveFeatureResponse responseModel = new RemoveFeatureResponse(id);
        presenter.present(responseModel);
    }
}
