package com.grantburgess.usecases.feature.get.featurebyid;

import com.grantburgess.entities.Feature;
import com.grantburgess.ports.database.FeatureGateway;
import com.grantburgess.ports.presenters.feature.FeatureOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.get.featurebyid.GetFeatureByIdInputBoundary;
import com.grantburgess.ports.usescases.feature.get.featurebyid.GetFeatureRequest;
import com.grantburgess.usecases.feature.get.GetFeatureBase;

public class GetFeatureById extends GetFeatureBase implements GetFeatureByIdInputBoundary {
    private final FeatureOutputBoundary presenter;
    private final FeatureGateway featureGateway;
    private final Clock clock;

    public GetFeatureById(FeatureOutputBoundary presenter, FeatureGateway featureGateway, Clock clock) {
        this.presenter = presenter;
        this.featureGateway = featureGateway;
        this.clock = clock;
    }

    public void execute(GetFeatureRequest request) {
        Feature offer = featureGateway.getByIdExcludingCancelled(request.getId());

        if (offer == null)
            throw new FeatureGateway.OfferNotFoundException();

        presenter.present(makeFeatureResponse(offer, clock));
    }
}
