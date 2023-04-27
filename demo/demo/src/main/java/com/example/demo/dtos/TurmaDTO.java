package com.example.demo.dtos;

import com.example.demo.models.Estudante;
import com.example.demo.models.Turma;

import java.util.List;
import java.util.stream.Collectors;

public record TurmaDTO(
        String nome,
        Integer serie,
        String turno
) {
    public TurmaDTO(Turma turma) {
        this (
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
