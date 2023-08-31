package com.grantburgess.ports.presenters.task;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskViewModel {
    private String id;
    private String name;
    private String description;
    private Duration duration;


    @Builder
    @Getter
    public static class Duration {
        private String startDate;
        private String endDate;
    }
}
