package com.grantburgess.application.endpoints.feature.getFeatures;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.feature.FeaturesOutputBoundary;
import com.grantburgess.ports.presenters.feature.FeaturesViewModel;
import com.grantburgess.ports.usescases.feature.get.features.GetFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.get.features.GetFeaturesRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/features")
public class GetFeaturesEndpoint implements BaseEndpoint {
    private final GetFeatureInputBoundary useCase;
    private final FeaturesOutputBoundary presenter;

    public GetFeaturesEndpoint(GetFeatureInputBoundary useCase, FeaturesOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get features", response = FeaturesViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetFeaturesRequest());
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
