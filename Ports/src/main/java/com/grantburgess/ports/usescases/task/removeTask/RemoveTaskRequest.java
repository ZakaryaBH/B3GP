package com.grantburgess.ports.usescases.task.removeTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RemoveTaskRequest {
    private UUID id;
}
