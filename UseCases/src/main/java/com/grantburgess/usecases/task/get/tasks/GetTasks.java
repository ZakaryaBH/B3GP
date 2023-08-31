package com.grantburgess.usecases.task.get.tasks;

import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TasksOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.get.tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.task.get.tasks.GetTasksRequest;
import com.grantburgess.ports.usescases.task.get.tasks.TasksResponse;
import com.grantburgess.usecases.task.get.GetTaskBase;

public class GetTasks extends GetTaskBase implements GetTaskInputBoundary {
    private final TasksOutputBoundary presenter;
    private final TaskGateway taskGateway;
    private final Clock clock;

    public GetTasks(TasksOutputBoundary presenter, TaskGateway taskGateway, Clock clock) {
        this.presenter = presenter;
        this.taskGateway = taskGateway;
        this.clock = clock;
    }

    public void execute(GetTasksRequest request) {
        TasksResponse.TasksResponseBuilder result = TasksResponse.builder();
        taskGateway.getAllExcludingCancelled().forEach(offer -> result.addTask(makeTaskResponse(offer, clock)));
        presenter.present(result.build());
    }
}
