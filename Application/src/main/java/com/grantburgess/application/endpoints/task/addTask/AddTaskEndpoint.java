package com.grantburgess.application.endpoints.task.addTask;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.task.addtask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.task.addtask.AddTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks")
public class AddTaskEndpoint implements BaseEndpoint {
    private final AddTaskInputBoundary useCase;
    private final TaskCreatedOutputBoundary presenter;

    public AddTaskEndpoint(AddTaskInputBoundary useCase, TaskCreatedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping
    @ApiOperation(value = "Add task", response = TaskCreatedViewModel.class)
    public ResponseEntity execute(@RequestBody @Valid NewTaskRequest request) {
        useCase.execute(
                AddTaskRequest
                        .builder()
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
