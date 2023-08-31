package com.grantburgess.ports.usescases.task.get.tasks;

import com.grantburgess.ports.usescases.task.get.TaskResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TasksResponse {
    @Singular("addTask") private List<TaskResponse> Tasks;
}
