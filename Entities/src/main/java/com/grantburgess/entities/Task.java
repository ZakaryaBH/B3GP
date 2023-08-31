package com.grantburgess.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Task {
    private  UUID id;
    private  String name;
    private  String description;
    private  LocalDate startDate;
    private  LocalDate endDate;

    public enum Status {
        ACTIVE, EXPIRED, CANCELLED
    }

    public Task(String name, String description, LocalDate startDate, LocalDate endDate) {
        this(UUID.randomUUID(), name, description, startDate, endDate);
    }

    public Task(UUID id, String name, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
