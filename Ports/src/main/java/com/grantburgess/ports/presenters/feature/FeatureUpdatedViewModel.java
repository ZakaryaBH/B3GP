package com.grantburgess.ports.presenters.feature;

public class FeatureUpdatedViewModel {
    private String id;

    public FeatureUpdatedViewModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
