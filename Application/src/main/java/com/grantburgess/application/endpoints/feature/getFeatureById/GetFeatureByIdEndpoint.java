package com.grantburgess.application.endpoints.feature.getFeatureById;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.feature.FeatureOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureViewModel;
import com.grantburgess.ports.usescases.feature.get.featurebyid.GetFeatureByIdInputBoundary;
import com.grantburgess.ports.usescases.feature.get.featurebyid.GetFeatureRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/features", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GetFeatureByIdEndpoint implements BaseEndpoint {
    private final GetFeatureByIdInputBoundary useCase;
    private final FeatureOutputBoundary presenter;

    public GetFeatureByIdEndpoint(GetFeatureByIdInputBoundary useCase, FeatureOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping("/{offerId}")
    @ApiOperation(value = "Get offer by ID", response = FeatureViewModel.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "offerId") String offerId) {
        useCase.execute(GetFeatureRequest.builder().id(UUID.fromString(offerId)).build());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}
