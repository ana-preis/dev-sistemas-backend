package com.example.demo.dtos;

import com.example.demo.models.Turma;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record TurmaDTO(
        Long id,
        String nome,
        @NotNull
        Integer serie,
        @NotEmpty
        String turno
) {
    public TurmaDTO(Turma turma) {
        this (
                turma.getId(),
                turma.getNome(),
                turma.getSerie(),
                turma.getTurno().toString()
        );
    }

    public static List<TurmaDTO> toTurmaDTOList(List<Turma> turmas) {
        return turmas.stream()
                .map(turma -> new TurmaDTO(turma))
                .collect(Collectors.toList());
    }
}
