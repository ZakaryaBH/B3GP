package com.grantburgess.ports.presenters.task;

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
public class TasksViewModel {
    @Singular("TasksViewModel") private List<TaskViewModel> tasks;
}
