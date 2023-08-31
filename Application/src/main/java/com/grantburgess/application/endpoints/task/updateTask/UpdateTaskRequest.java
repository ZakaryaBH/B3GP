package com.grantburgess.application.endpoints.task.updateTask;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ApiModel(value = "New Offer Request", subTypes = UpdateTaskRequest.Duration.class)
public class UpdateTaskRequest {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    @ApiModelProperty(notes = "Offer name", position = 2, example = "Offer name 01")
    private String name;
    @ApiModelProperty(notes = "Offer description", position = 3, example = "Offer description 01")
    private String description;
    @NotNull
    @Valid
    @ApiModelProperty(notes = "Offer duration", position = 4)
    private Duration duration;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "Offer duration", parent = UpdateTaskRequest.class)
    public static class Duration {
        @NotNull
        @ApiModelProperty(notes = "Offer start date", position = 1, example = "2020-01-01")
        private LocalDate startDate;
        @NotNull
        @ApiModelProperty(notes = "Offer end date", position = 2, example = "2020-01-31")
        private LocalDate endDate;
    }
}