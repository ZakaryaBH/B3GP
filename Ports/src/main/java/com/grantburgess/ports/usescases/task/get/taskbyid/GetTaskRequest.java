package com.grantburgess.ports.usescases.task.get.taskbyid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetTaskRequest {
    private UUID id;
}
