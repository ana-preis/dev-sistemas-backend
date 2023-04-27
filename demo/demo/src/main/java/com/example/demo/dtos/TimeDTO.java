package com.example.demo.dtos;

import com.example.demo.models.Time;

import java.util.List;

import static com.example.demo.dtos.EstudanteDTO.toEstudanteDTOList;

public record TimeDTO(
        List<Long> estudanteIds,
        String nome,
        String esporte,
        Long idCapitao,
        Long idTurma

) {
    public TimeDTO(Time time) {
        this (
                toEstudanteDTOList(time.getEstudantes()),
                time.getNome(),
                time.getEsporte().toString(),
                time.getCapitao().getId(),
                time.getTurma().getId()
        );
    }
}
