package com.grantburgess.usecases.task.updatetask;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TaskUpdatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.ports.usescases.task.updateTask.UpdateTaskResponse;
import com.grantburgess.ports.usescases.task.updateTask.UpdatepTaskRequest;

import java.util.UUID;

public class UpdateTask implements UpdateTaskInputBoundary {
    private final TaskUpdatedOutputBoundary presenter;
    private final TaskGateway taskGateway;
    private final Clock clock;

    public UpdateTask(TaskUpdatedOutputBoundary presenter, TaskGateway taskGateway, Clock clock) {
        this.presenter = presenter;
        this.taskGateway = taskGateway;
        this.clock = clock;
    }

    public void execute(UpdatepTaskRequest request) {
        validateOffer(request);
        UUID id = updateTask(request);

        UpdateTaskResponse responseModel = new UpdateTaskResponse(id);
        presenter.present(responseModel);
    }

    private void validateOffer(final UpdatepTaskRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new TaskGateway.OfferStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new TaskGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException();
        if (offernotAlreadyExists(request))
            throw new TaskGateway.OfferNameAlreadyExistsException();
    }

    private boolean offernotAlreadyExists(final UpdatepTaskRequest request) {
        return !taskGateway.existsByName(request.getName());
    }

    private UUID updateTask(UpdatepTaskRequest request) {
        return taskGateway.update(
                new Task(
                        request.getId(),
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate()
                )
        );
    }
}
