package com.grantburgess.usecases.task.get.taskbyid;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TaskOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.usecases.task.get.GetTaskBase;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskRequest;

public class GetTaskById extends GetTaskBase implements GetTaskByIdInputBoundary {
    private final TaskOutputBoundary presenter;
    private final TaskGateway taskGateway;
    private final Clock clock;

    public GetTaskById(TaskOutputBoundary presenter, TaskGateway taskGateway, Clock clock) {
        this.presenter = presenter;
        this.taskGateway = taskGateway;
        this.clock = clock;
    }

    public void execute(GetTaskRequest request) {
        Task offer = taskGateway.getByIdExcludingCancelled(request.getId());

        if (offer == null)
            throw new TaskGateway.OfferNotFoundException();

        presenter.present(makeTaskResponse(offer, clock));
    }
}
