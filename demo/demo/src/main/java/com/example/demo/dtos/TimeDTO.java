package com.example.demo.dtos;

import com.example.demo.models.Time;

import java.util.List;
import java.util.stream.Collectors;

public record TimeDTO(
        Long id,
        String nome,
        String esporte,
        Long idCapitao,
        Long idTurma

) {
    public TimeDTO(Time time) {
        this (
                time.getId(),
                time.getNome(),
                time.getEsporte().toString(),
                time.getCapitao().getId(),
                time.getTurma().getId()
        );
    }

    public static List<TimeDTO> toTimeDTOList(List<Time> times) {
        return times.stream()
                .map(time -> new TimeDTO(time))
                .collect(Collectors.toList());
    }
}
