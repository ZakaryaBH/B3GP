package com.grantburgess.usecases.feature.addfeature;

import com.grantburgess.entities.Feature;
import com.grantburgess.ports.database.FeatureGateway;
import com.grantburgess.ports.presenters.feature.FeatureCreatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.addfeature.AddFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.addfeature.AddFeatureRequest;
import com.grantburgess.ports.usescases.feature.addfeature.NewFeatureResponse;

import java.util.UUID;

public class AddFeature implements AddFeatureInputBoundary {
    private final FeatureCreatedOutputBoundary presenter;
    private final FeatureGateway featureGateway;
    private final Clock clock;

    public AddFeature(FeatureCreatedOutputBoundary presenter, FeatureGateway featureGateway, Clock clock) {
        this.presenter = presenter;
        this.featureGateway = featureGateway;
        this.clock = clock;
    }

    public void execute(AddFeatureRequest request) {
        validateOffer(request);
        UUID id = addOffer(request);

        NewFeatureResponse responseModel = new NewFeatureResponse(id);
        presenter.present(responseModel);
    }

    private void validateOffer(final AddFeatureRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new FeatureGateway.OfferStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new FeatureGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException();
        if (offerAlreadyExists(request))
            throw new FeatureGateway.OfferNameAlreadyExistsException();
    }

    private boolean offerAlreadyExists(final AddFeatureRequest request) {
        return featureGateway.existsByName(request.getName());
    }

    private UUID addOffer(AddFeatureRequest request) {
        return featureGateway.add(
                new Feature(
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate(),
                        request.getIdLot())
        );
    }
}
