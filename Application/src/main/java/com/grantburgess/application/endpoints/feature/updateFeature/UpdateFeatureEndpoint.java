package com.grantburgess.application.endpoints.feature.updateFeature;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.feature.FeatureUpdatedOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureUpdatedViewModel;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdateFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdatepFeatureRequest;
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
@RequestMapping("/features")
public class UpdateFeatureEndpoint implements BaseEndpoint {
    private final UpdateFeatureInputBoundary useCase;
    private final FeatureUpdatedOutputBoundary presenter;

    public UpdateFeatureEndpoint(UpdateFeatureInputBoundary useCase, FeatureUpdatedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping("/update/{featureId}")
    @ApiOperation(value = "update feature", response = FeatureUpdatedViewModel.class)
    public ResponseEntity execute(@ApiParam(value = "ID of the feature that needs to be updated", required = true) @PathVariable(value = "featureId") String featureId, @RequestBody @Valid UpdateFeatureRequest request) {
        useCase.execute(
                UpdatepFeatureRequest
                        .builder()
                        .id(UUID.fromString(featureId))
                        .name(request.getName())
                        .description(request.getDescription())
                        .startDate(request.getDuration().getStartDate())
                        .endDate(request.getDuration().getEndDate())
                        .build()
        );

        return ResponseEntity
                .created(
                        URI.create(
                                MessageFormat.format("/features/{0}", presenter.getViewModel().getId())
                        )
                )
                .body(presenter.getViewModel());
    }
}