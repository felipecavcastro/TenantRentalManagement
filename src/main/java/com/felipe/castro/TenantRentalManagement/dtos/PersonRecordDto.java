package com.felipe.castro.TenantRentalManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRecordDto(@NotBlank String name, @NotNull long documentNumber) {
}
