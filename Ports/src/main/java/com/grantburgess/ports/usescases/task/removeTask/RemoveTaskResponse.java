package com.grantburgess.ports.usescases.task.removeTask;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RemoveTaskResponse {
    private UUID id;
    public RemoveTaskResponse(UUID id) {
        this.id = id;
    }
}
