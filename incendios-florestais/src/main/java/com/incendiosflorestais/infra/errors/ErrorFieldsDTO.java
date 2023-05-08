package com.incendiosflorestais.infra.errors;

import org.springframework.validation.FieldError;

public record ErrorFieldsDTO(
        String campo,
        String erro
) {
    public ErrorFieldsDTO(FieldError error) {
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
