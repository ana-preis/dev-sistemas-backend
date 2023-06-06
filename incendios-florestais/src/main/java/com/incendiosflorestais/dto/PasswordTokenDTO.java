package com.incendiosflorestais.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PasswordTokenDTO(
        String token,
        @NotEmpty
        @Size(min = 6, max = 24, message = "password should have between 6 and 24 characters")
        String newPassword,
        String oldPassword
) {
}
