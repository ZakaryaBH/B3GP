package com.grantburgess.application.endpoints.feature.removeFeature;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.feature.FeatureRemovedOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureRemovedViewModel;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/features", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RemoveFeatureEndpoint implements BaseEndpoint {
    private final RemoveFeatureInputBoundary useCase;
    private final FeatureRemovedOutputBoundary presenter;

    public RemoveFeatureEndpoint(RemoveFeatureInputBoundary useCase, FeatureRemovedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @DeleteMapping("/{offerId}")
    @ApiOperation(value = "Get offer by ID", response = FeatureRemovedViewModel.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "offerId") String offerId) {
        useCase.execute(RemoveFeatureRequest.builder().id(UUID.fromString(offerId)).build());
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
