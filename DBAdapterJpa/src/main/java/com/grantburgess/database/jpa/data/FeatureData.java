package com.grantburgess.database.jpa.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Feature")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureData {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID idLot;
}
