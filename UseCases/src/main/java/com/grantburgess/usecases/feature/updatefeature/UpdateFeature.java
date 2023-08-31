package com.grantburgess.usecases.feature.updatefeature;

import com.grantburgess.entities.Feature;
import com.grantburgess.ports.database.FeatureGateway;
import com.grantburgess.ports.presenters.feature.FeatureUpdatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdateFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdateFeatureResponse;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdatepFeatureRequest;

import java.util.UUID;

public class UpdateFeature implements UpdateFeatureInputBoundary {
    private final FeatureUpdatedOutputBoundary presenter;
    private final FeatureGateway featureGateway;
    private final Clock clock;

    public UpdateFeature(FeatureUpdatedOutputBoundary presenter, FeatureGateway featureGateway, Clock clock) {
        this.presenter = presenter;
        this.featureGateway = featureGateway;
        this.clock = clock;
    }

    public void execute(UpdatepFeatureRequest request) {
        validateOffer(request);
        UUID id = updateFeature(request);

        UpdateFeatureResponse responseModel = new UpdateFeatureResponse(id);
        presenter.present(responseModel);
    }

    private void validateOffer(final UpdatepFeatureRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new FeatureGateway.OfferStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new FeatureGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException();
        if (offernotAlreadyExists(request))
            throw new FeatureGateway.OfferNameAlreadyExistsException();
    }

    private boolean offernotAlreadyExists(final UpdatepFeatureRequest request) {
        return !featureGateway.existsByName(request.getName());
    }

    private UUID updateFeature(UpdatepFeatureRequest request) {
        return featureGateway.update(
                new Feature(
                        request.getId(),
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate()
                )
        );
    }
}
