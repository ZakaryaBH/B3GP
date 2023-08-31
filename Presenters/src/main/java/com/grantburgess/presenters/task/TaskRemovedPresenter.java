package com.grantburgess.presenters.task;

import com.grantburgess.ports.presenters.task.TaskRemovedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskRemovedViewModel;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskResponse;

public class TaskRemovedPresenter implements TaskRemovedOutputBoundary {
    private TaskRemovedViewModel viewModel;

    public TaskRemovedViewModel getViewModel() {
        return viewModel;
    }

    public void present(RemoveTaskResponse responseModel) {
        viewModel = new TaskRemovedViewModel(responseModel.getId().toString());
    }
}
