package com.grantburgess.usecases.testdoubles;

import com.grantburgess.ports.presenters.task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskViewModel;
import com.grantburgess.ports.usescases.task.get.TaskResponse;

public class TaskPresenterSpy implements TaskOutputBoundary {
    private boolean isOfferPresented;
    private TaskResponse responseModel;

    public TaskViewModel getViewModel() {
        return null;
    }

    public void present(TaskResponse responseModel) {
        isOfferPresented = true;
        this.responseModel = responseModel;
    }

    public boolean isOfferPresented() {
        return isOfferPresented;
    }

    public TaskResponse getResponseModel() {
        return responseModel;
    }
}
