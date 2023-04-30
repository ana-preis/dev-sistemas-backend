package com.example.demo.dtos;

import com.example.demo.models.Time;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.dtos.EstudanteDTO.toEstudanteDTOList;

public record TimeDTO(
        Long id,
        String nome,
        String esporte,
        Long idCapitao,
        Long idTurma,
        List<EstudanteDTO> estudantes

) {
    public TimeDTO(Time time) {
        this (
                time.getId(),
                time.getNome(),
                time.getEsporte().toString(),
                time.getCapitao() != null ? time.getCapitao().getId() : null,
                time.getTurma() != null ? time.getTurma().getId() : null,
                time.getEstudantes() != null ? toEstudanteDTOList(time.getEstudantes()) : null
        );
    }

    public static List<TimeDTO> toTimeDTOList(List<Time> times) {
        return times.stream()
                .map(TimeDTO::new)
                .collect(Collectors.toList());
    }
}
