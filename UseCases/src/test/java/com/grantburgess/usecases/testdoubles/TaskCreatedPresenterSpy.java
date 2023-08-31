package com.grantburgess.usecases.testdoubles;

import com.grantburgess.ports.presenters.task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.task.addtask.NewTaskResponse;

public class TaskCreatedPresenterSpy implements TaskCreatedOutputBoundary {
    private boolean isOfferPresented;
    private NewTaskResponse responseModel;

    public TaskCreatedViewModel getViewModel() {
        return null;
    }

    public void present(NewTaskResponse responseModel) {
        isOfferPresented = true;
        this.responseModel = responseModel;
    }

    public boolean isOfferPresented() {
        return isOfferPresented;
    }

    public NewTaskResponse getResponseModel() {
        return responseModel;
    }
}
