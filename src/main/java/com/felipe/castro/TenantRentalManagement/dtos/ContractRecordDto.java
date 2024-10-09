package com.felipe.castro.TenantRentalManagement.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class ContractRecordDto {

    @NotNull
    private UUID personId;

    @NotNull
    private UUID realEstateId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private double rentalValue;

    // Getters and Setters
}
