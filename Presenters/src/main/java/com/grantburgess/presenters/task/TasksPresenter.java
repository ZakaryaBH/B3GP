package com.grantburgess.presenters.task;

import com.grantburgess.ports.presenters.task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.task.TasksViewModel;
import com.grantburgess.ports.usescases.task.get.tasks.TasksResponse;

public class TasksPresenter extends BaseTaskPresenter implements TasksOutputBoundary {
    private TasksViewModel viewModel;

    public TasksViewModel getViewModel() {
        return viewModel;
    }

    public void present(TasksResponse responseModel) {
        TasksViewModel.TasksViewModelBuilder offersViewModelBuilder = TasksViewModel.builder();
        responseModel.getTasks()
                .stream()
                .map(BaseTaskPresenter::mapToTaskViewModel)
                .forEach(offersViewModelBuilder::TasksViewModel);
        viewModel = offersViewModelBuilder.build();
    }
}