package com.grantburgess.ports.presenters.task;

import com.grantburgess.ports.usescases.task.updateTask.UpdateTaskResponse;

public interface TaskUpdatedOutputBoundary {
    TaskUpdatedViewModel getViewModel();
    void present(UpdateTaskResponse responseModel);
}
