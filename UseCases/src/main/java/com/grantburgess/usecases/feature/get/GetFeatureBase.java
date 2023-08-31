package com.grantburgess.usecases.feature.get;

import com.grantburgess.entities.Feature;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.get.FeatureResponse;

public abstract class GetFeatureBase {
    protected GetFeatureBase() { }

    public static FeatureResponse makeFeatureResponse(Feature offer, Clock clock) {
        return new FeatureResponse(
                offer.getId(),
                offer.getName(),
                offer.getDescription(),
                offer.getStartDate(),
                offer.getEndDate(),
                offer.getIdLot());
    }
}
