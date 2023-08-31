package com.grantburgess.application.endpoints.feature.addFeature;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.feature.FeatureCreatedOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeatureCreatedViewModel;
import com.grantburgess.ports.usescases.feature.addfeature.AddFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.addfeature.AddFeatureRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/features")
public class AddFeatureEndpoint implements BaseEndpoint {
    private final AddFeatureInputBoundary useCase;
    private final FeatureCreatedOutputBoundary presenter;

    public AddFeatureEndpoint(AddFeatureInputBoundary useCase, FeatureCreatedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping
    @ApiOperation(value = "Add feature", response = FeatureCreatedViewModel.class)
    public ResponseEntity execute(@RequestBody @Valid NewFeatureRequest request) {
        useCase.execute(
                AddFeatureRequest
                        .builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .startDate(request.getDuration().getStartDate())
                        .endDate(request.getDuration().getEndDate())
                        .idLot(request.getIdLot())
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
