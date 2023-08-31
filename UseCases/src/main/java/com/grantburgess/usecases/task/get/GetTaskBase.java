package com.grantburgess.usecases.task.get;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.get.TaskResponse;

public abstract class GetTaskBase {
    protected GetTaskBase() { }

    public static TaskResponse makeTaskResponse(Task offer, Clock clock) {
        return new TaskResponse(
                offer.getId(),
                offer.getName(),
                offer.getDescription(),
                offer.getStartDate(),
                offer.getEndDate());
    }
}
