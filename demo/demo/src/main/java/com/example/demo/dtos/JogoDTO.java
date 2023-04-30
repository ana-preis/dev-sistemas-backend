package com.example.demo.dtos;

import com.example.demo.models.Jogo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record JogoDTO(
        Long id,
        @NotNull
        Long idTime1,
        @NotNull
        Long idTime2,
        @NotEmpty
        String esporte,
        @NotNull
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime data,
        Integer placar_time_1,
        Integer placat_time_2

) {
    public JogoDTO(Jogo jogo) {
        this (
                jogo.getId(),
                jogo.getTime1() != null ? jogo.getTime1().getId() : null,
                jogo.getTime2() != null ? jogo.getTime2().getId() : null,
                jogo.getEsporte().toString(),
                jogo.getData(),
                jogo.getPlacar_time1() != null ? jogo.getPlacar_time1() : null,
                jogo.getPlacar_time2() != null ? jogo.getPlacar_time2() : null
        );
    }

    public static List<JogoDTO> toJogoDTOList(List<Jogo> jogos) {
        return jogos.stream()
                .map(JogoDTO::new)
                .collect(Collectors.toList());
    }
}
