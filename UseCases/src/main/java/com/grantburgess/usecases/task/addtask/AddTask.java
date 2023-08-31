package com.grantburgess.usecases.task.addtask;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.addtask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.task.addtask.AddTaskRequest;
import com.grantburgess.ports.usescases.task.addtask.NewTaskResponse;

import java.util.UUID;

public class AddTask implements AddTaskInputBoundary {
    private final TaskCreatedOutputBoundary presenter;
    private final TaskGateway taskGateway;
    private final Clock clock;

    public AddTask(TaskCreatedOutputBoundary presenter, TaskGateway taskGateway, Clock clock) {
        this.presenter = presenter;
        this.taskGateway = taskGateway;
        this.clock = clock;
    }

    public void execute(AddTaskRequest request) {
        validateOffer(request);
        UUID id = addOffer(request);

        NewTaskResponse responseModel = new NewTaskResponse(id);
        presenter.present(responseModel);
    }

    private void validateOffer(final AddTaskRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new TaskGateway.OfferStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new TaskGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException();
        if (offerAlreadyExists(request))
            throw new TaskGateway.OfferNameAlreadyExistsException();
    }

    private boolean offerAlreadyExists(final AddTaskRequest request) {
        return taskGateway.existsByName(request.getName());
    }

    private UUID addOffer(AddTaskRequest request) {
        return taskGateway.add(
                new Task(
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate()
                )
        );
    }
}
