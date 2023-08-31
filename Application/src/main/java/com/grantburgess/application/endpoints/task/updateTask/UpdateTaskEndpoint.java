package com.grantburgess.application.endpoints.task.updateTask;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.task.TaskUpdatedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskUpdatedViewModel;
import com.grantburgess.ports.usescases.task.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.ports.usescases.task.updateTask.UpdatepTaskRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks")
public class UpdateTaskEndpoint implements BaseEndpoint {
    private final UpdateTaskInputBoundary useCase;
    private final TaskUpdatedOutputBoundary presenter;

    public UpdateTaskEndpoint(UpdateTaskInputBoundary useCase, TaskUpdatedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping("/update/{taskId}")
    @ApiOperation(value = "update task", response = TaskUpdatedViewModel.class)
    public ResponseEntity execute(@ApiParam(value = "ID of the task that needs to be updated", required = true) @PathVariable(value = "taskId") String taskId, @RequestBody @Valid UpdateTaskRequest request) {
        useCase.execute(
                UpdatepTaskRequest
                        .builder()
                        .id(UUID.fromString(taskId))
                        .name(request.getName())
                        .description(request.getDescription())
                        .startDate(request.getDuration().getStartDate())
                        .endDate(request.getDuration().getEndDate())
                        .build()
        );

        return ResponseEntity
                .created(
                        URI.create(
                                MessageFormat.format("/tasks/{0}", presenter.getViewModel().getId())
                        )
                )
                .body(presenter.getViewModel());
    }
}