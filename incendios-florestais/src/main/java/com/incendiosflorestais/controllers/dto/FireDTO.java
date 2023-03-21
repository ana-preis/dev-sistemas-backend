package com.incendiosflorestais.controllers.dto;

import com.incendiosflorestais.models.Identification;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record FireDTO(
        @NotEmpty
        Float latitude,
        @NotEmpty
        Float longitude,
        @NotEmpty
        LocalDateTime start,
        @NotEmpty
        LocalDateTime end,
        @NotEmpty
        LocalDateTime savedAt,
        @NotEmpty
        UserDTO user,
        Identification identification,
        String satelliteName
) {}
