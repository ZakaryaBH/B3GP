package com.grantburgess.ports.usescases.feature.addfeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddFeatureRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID idLot;
}
