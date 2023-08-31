package com.grantburgess.ports.presenters.feature;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class FeatureViewModel {
    private String id;
    private String name;
    private String description;
    private Duration duration;
    private UUID idLot;

    @Builder
    @Getter
    public static class Duration {
        private String startDate;
        private String endDate;
    }
}
