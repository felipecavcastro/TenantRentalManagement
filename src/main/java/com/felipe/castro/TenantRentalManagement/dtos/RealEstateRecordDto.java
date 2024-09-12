package com.felipe.castro.TenantRentalManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RealEstateRecordDto(@NotBlank String rua, @NotNull int numero) {
}
