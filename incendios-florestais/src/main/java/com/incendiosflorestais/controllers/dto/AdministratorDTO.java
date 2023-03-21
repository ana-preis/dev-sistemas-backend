package com.incendiosflorestais.controllers.dto;

public record AdministratorDTO(
        boolean isAdmin
) {
    public AdministratorDTO(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
