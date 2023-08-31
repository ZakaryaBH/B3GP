package com.grantburgess.application.endpoints.task.removeTask;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.task.TaskRemovedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskRemovedViewModel;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskInputBoundary;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RemoveTaskEndpoint implements BaseEndpoint {
    private final RemoveTaskInputBoundary useCase;
    private final TaskRemovedOutputBoundary presenter;

    public RemoveTaskEndpoint(RemoveTaskInputBoundary useCase, TaskRemovedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @DeleteMapping("/{offerId}")
    @ApiOperation(value = "Get offer by ID", response = TaskRemovedViewModel.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "offerId") String offerId) {
        useCase.execute(RemoveTaskRequest.builder().id(UUID.fromString(offerId)).build());
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
