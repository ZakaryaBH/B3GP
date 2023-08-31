package com.grantburgess.ports.presenters.task;

import com.grantburgess.ports.usescases.task.addtask.NewTaskResponse;

public interface TaskCreatedOutputBoundary {
    TaskCreatedViewModel getViewModel();
    void present(NewTaskResponse responseModel);
}
