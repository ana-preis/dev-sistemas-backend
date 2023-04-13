package com.incendiosflorestais.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty
        @Size(min = 1, message = "first name should have at least one character")
        String firstName,
        @NotEmpty
        @Size(min = 1, message = "last name should have at least one character")
        String lastName,
        @NotEmpty
        @Size(min = 5, message = "email should have at least 5 characters")
        String email,
        @NotEmpty
        @Size(min = 6, message = "password should have at least 6 characters")
        String password
) {
}
