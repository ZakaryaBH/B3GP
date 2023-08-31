package com.grantburgess.ports.presenters.task;

import com.grantburgess.ports.usescases.task.get.tasks.TasksResponse;

public interface TasksOutputBoundary {
    TasksViewModel getViewModel();
    void present(TasksResponse responseModel);
}
