package com.grantburgess.ports.presenters.feature;

public class FeatureRemovedViewModel {
    private String id;

    public FeatureRemovedViewModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
