package com.incendiosflorestais.dto;

import com.incendiosflorestais.models.Identification;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record ImageDTO(
        @NotEmpty
        String filename,
        Identification type,
        @NotEmpty
        LocalDateTime date
) {
    public ImageDTO(@NotEmpty
                    String filename, Identification type, @NotEmpty
                    LocalDateTime date) {
        this.filename = filename;
        this.type = type;
        this.date = date;
    }
}
