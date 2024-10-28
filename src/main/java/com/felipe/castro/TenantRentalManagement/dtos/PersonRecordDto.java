package com.felipe.castro.TenantRentalManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRecordDto(Integer id, @NotBlank String name, String cpf, @NotNull String email) {
}
