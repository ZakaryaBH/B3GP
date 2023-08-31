package com.grantburgess.ports.usescases.task.updateTask;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTaskResponse {
    private UUID id;

    public UpdateTaskResponse(UUID id) {
        this.id = id;
    }
}
