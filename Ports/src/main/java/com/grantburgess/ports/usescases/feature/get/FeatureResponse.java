package com.grantburgess.ports.usescases.feature.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID idLot;
}
