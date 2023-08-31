package com.grantburgess.ports.usescases.task.addtask;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewTaskResponse {
    private UUID id;

    public NewTaskResponse(UUID id) {
        this.id = id;
    }
}
