package com.grantburgess.ports.presenters.task;

import com.grantburgess.ports.usescases.task.get.TaskResponse;

public interface TaskOutputBoundary {
    TaskViewModel getViewModel();
    void present(TaskResponse responseModel);
}
