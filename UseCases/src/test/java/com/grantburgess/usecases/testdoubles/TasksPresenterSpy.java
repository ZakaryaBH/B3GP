package com.grantburgess.usecases.testdoubles;

import com.grantburgess.ports.presenters.task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.task.TasksViewModel;
import com.grantburgess.ports.usescases.task.get.tasks.TasksResponse;

public class TasksPresenterSpy implements TasksOutputBoundary {
    private boolean isOffersPresented;
    private TasksResponse responseModel;

    public TasksViewModel getViewModel() {
        return null;
    }

    public void present(TasksResponse responseModel) {
        isOffersPresented = true;
        this.responseModel = responseModel;
    }

    public boolean isOffersPresented() {
        return isOffersPresented;
    }

    public TasksResponse getResponseModel() {
        return responseModel;
    }
}
