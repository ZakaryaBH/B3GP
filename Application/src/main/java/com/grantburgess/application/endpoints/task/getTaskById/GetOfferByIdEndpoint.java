package com.grantburgess.application.endpoints.task.getTaskById;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskViewModel;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GetOfferByIdEndpoint implements BaseEndpoint {
    private final GetTaskByIdInputBoundary useCase;
    private final TaskOutputBoundary presenter;

    public GetOfferByIdEndpoint(GetTaskByIdInputBoundary useCase, TaskOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping("/{offerId}")
    @ApiOperation(value = "Get offer by ID", response = TaskViewModel.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "offerId") String offerId) {
        useCase.execute(GetTaskRequest.builder().id(UUID.fromString(offerId)).build());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}
