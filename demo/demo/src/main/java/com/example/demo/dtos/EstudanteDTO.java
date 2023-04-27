package com.example.demo.dtos;

import com.example.demo.models.Estudante;

import java.util.List;
import java.util.stream.Collectors;

public record EstudanteDTO(
        Long idTurma,
        String matricula

) {
    public EstudanteDTO(Estudante estudante){
        this (
                estudante.getTurma().getId(),
                estudante.getMatricula()
        );
    }

    public static List<EstudanteDTO> toEstudanteDTOList(List<Estudante> estudantes) {
        return estudantes.stream()
                .map(estudante -> new EstudanteDTO(estudante))
                .collect(Collectors.toList());
    }
}
