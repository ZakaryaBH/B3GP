package com.grantburgess.ports.presenters.feature;

public class FeatureCreatedViewModel {
    private String id;

    public FeatureCreatedViewModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
