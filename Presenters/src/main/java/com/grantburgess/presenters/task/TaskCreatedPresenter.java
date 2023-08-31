package com.grantburgess.presenters.task;

import com.grantburgess.ports.presenters.task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.task.addtask.NewTaskResponse;

public class TaskCreatedPresenter implements TaskCreatedOutputBoundary {
    private TaskCreatedViewModel viewModel;

    public TaskCreatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(NewTaskResponse responseModel) {
        viewModel = new TaskCreatedViewModel(responseModel.getId().toString());
    }
}
