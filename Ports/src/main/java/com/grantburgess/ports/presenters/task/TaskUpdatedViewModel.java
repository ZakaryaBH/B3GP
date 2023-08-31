package com.grantburgess.ports.presenters.task;

public class TaskUpdatedViewModel {
    private String id;

    public TaskUpdatedViewModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
