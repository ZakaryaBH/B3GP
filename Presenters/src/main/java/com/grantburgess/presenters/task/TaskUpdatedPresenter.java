package com.grantburgess.presenters.task;

import com.grantburgess.ports.presenters.task.TaskUpdatedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskUpdatedViewModel;
import com.grantburgess.ports.usescases.task.updateTask.UpdateTaskResponse;

public class TaskUpdatedPresenter implements TaskUpdatedOutputBoundary {
    private TaskUpdatedViewModel viewModel;

    public TaskUpdatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(UpdateTaskResponse responseModel) {
        viewModel = new TaskUpdatedViewModel(responseModel.getId().toString());
    }
}
