package com.grantburgess.presenters.task;

import com.grantburgess.ports.presenters.task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskViewModel;
import com.grantburgess.ports.usescases.task.get.TaskResponse;

public class TaskPresenter extends BaseTaskPresenter implements TaskOutputBoundary {
    private TaskViewModel viewModel;

    public TaskViewModel getViewModel() {
        return viewModel;
    }

    public void present(TaskResponse responseModel) {
        viewModel = mapToTaskViewModel(responseModel);
    }
}
