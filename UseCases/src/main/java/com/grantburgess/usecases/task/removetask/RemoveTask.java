package com.grantburgess.usecases.task.removetask;

import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TaskRemovedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskInputBoundary;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskRequest;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskResponse;

import java.util.UUID;

public class RemoveTask implements RemoveTaskInputBoundary {
    private final TaskRemovedOutputBoundary presenter;
    private final TaskGateway taskGateway;
    private final Clock clock;

    public RemoveTask(TaskRemovedOutputBoundary presenter, TaskGateway taskGateway, Clock clock) {
        this.presenter = presenter;
        this.taskGateway = taskGateway;
        this.clock = clock;
    }

    public void execute(RemoveTaskRequest request) {
        UUID id = taskGateway.remove(request.getId());
        if (id == null)
            throw new TaskGateway.OfferNotFoundException();
        RemoveTaskResponse responseModel = new RemoveTaskResponse(id);
        presenter.present(responseModel);
    }
}
