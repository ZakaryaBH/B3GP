package com.grantburgess.ports.presenters.task;

public class TaskRemovedViewModel {
    private String id;

    public TaskRemovedViewModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
