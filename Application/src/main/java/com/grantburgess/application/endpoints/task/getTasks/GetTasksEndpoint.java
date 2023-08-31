package com.grantburgess.application.endpoints.task.getTasks;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.task.TasksViewModel;
import com.grantburgess.ports.usescases.task.get.tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.task.get.tasks.GetTasksRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks")
public class GetTasksEndpoint implements BaseEndpoint {
    private final GetTaskInputBoundary useCase;
    private final TasksOutputBoundary presenter;

    public GetTasksEndpoint(GetTaskInputBoundary useCase, TasksOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get tasks", response = TasksViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetTasksRequest());
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
