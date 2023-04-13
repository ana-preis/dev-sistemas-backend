package com.incendiosflorestais.dto;

public record AdministratorDTO(
        boolean isAdmin
) {
    public AdministratorDTO(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
