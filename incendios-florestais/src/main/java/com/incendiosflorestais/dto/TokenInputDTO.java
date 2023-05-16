package com.incendiosflorestais.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record TokenInputDTO(
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password
) {
}
