package com.grantburgess.presenters.feature;

import com.grantburgess.ports.presenters.feature.FeatureViewModel;
import com.grantburgess.ports.usescases.feature.get.FeatureResponse;

import java.time.format.DateTimeFormatter;

public class BaseFeaturePresenter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    protected BaseFeaturePresenter() { }

    public static FeatureViewModel mapToFeatureViewModel(FeatureResponse responseModel) {
        return FeatureViewModel
                .builder()

                .id(responseModel.getId().toString())
                .name(responseModel.getName())
                .description(responseModel.getDescription())
                .duration(getFeatureDuration(responseModel))
                .idLot(responseModel.getIdLot())
                .build();
    }

    private static FeatureViewModel.Duration getFeatureDuration(FeatureResponse responseModel) {
        String startDate = responseModel.getStartDate().format(DATE_TIME_FORMATTER);
        String endDate = responseModel.getEndDate().format(DATE_TIME_FORMATTER);
        return FeatureViewModel.Duration
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
