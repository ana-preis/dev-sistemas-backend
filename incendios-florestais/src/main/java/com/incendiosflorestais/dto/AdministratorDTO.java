package com.incendiosflorestais.dto;

import com.incendiosflorestais.models.Administrator;

public record AdministratorDTO(
        Long userID,
        boolean isAdmin
) {
    public AdministratorDTO(Administrator adm) {
        this(
                adm.getId(),
                adm.isAdmin()
        );
    }
}
