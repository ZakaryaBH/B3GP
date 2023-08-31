package com.grantburgess.ports.presenters.task;

import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskResponse;

public interface TaskRemovedOutputBoundary {
    TaskRemovedViewModel getViewModel();
    void present(RemoveTaskResponse responseModel);
}
