package com.grantburgess.entities;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
public class Feature {
    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID idLot;



    public Feature(String name, String description, LocalDate startDate, LocalDate endDate) {
        this(UUID.randomUUID(), name, description, startDate, endDate);
    }

    public Feature(UUID id, String name, String description, LocalDate startDate, LocalDate endDate, UUID idLot) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idLot = idLot;
    }

    public Feature(String name, String description, LocalDate startDate, LocalDate endDate, UUID idLot) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idLot = idLot;
    }

    public Feature(UUID id, String name, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
