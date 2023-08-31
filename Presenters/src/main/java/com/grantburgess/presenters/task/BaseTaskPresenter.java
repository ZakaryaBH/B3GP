package com.grantburgess.presenters.task;

import com.grantburgess.ports.presenters.task.TaskViewModel;
import com.grantburgess.ports.usescases.task.get.TaskResponse;

import java.time.format.DateTimeFormatter;

public class BaseTaskPresenter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    protected BaseTaskPresenter() { }

    public static TaskViewModel mapToTaskViewModel(TaskResponse responseModel) {
        return TaskViewModel
                .builder()

                .id(responseModel.getId().toString())
                .name(responseModel.getName())
                .description(responseModel.getDescription())
                .duration(getTaskDuration(responseModel))
                .build();
    }

    private static TaskViewModel.Duration getTaskDuration(TaskResponse responseModel) {
        String startDate = responseModel.getStartDate().format(DATE_TIME_FORMATTER);
        String endDate = responseModel.getEndDate().format(DATE_TIME_FORMATTER);
        return TaskViewModel.Duration
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
