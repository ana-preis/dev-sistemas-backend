package com.incendiosflorestais.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ParkAddressDTO(
        @NotEmpty
        @Size(min = 3, message = "city should have at least 3 characters")
        String city,
        @NotEmpty
        @Size(min = 2, message = "state should have at least 2 characters")
        String state,
        @NotEmpty
        @Size(min = 3, message = "country should have at least 3 characters")
        String country,
        @NotEmpty
        @Size(min = 3, message = "parkName should have at least 3 characters")
        String parkName
) {}
