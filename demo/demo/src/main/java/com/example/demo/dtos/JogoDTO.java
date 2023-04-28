package com.example.demo.dtos;

import com.example.demo.models.Esporte;
import com.example.demo.models.Estudante;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record JogoDTO(
        Long id,
        @NotEmpty
        Long idTime1,
        @NotEmpty
        Long idTime2,
        @NotEmpty
        String esporte,
        @NotEmpty
        LocalDateTime data

) {
    public JogoDTO(Jogo jogo, Long idTime1, Long idTime2) {
        this (
                jogo.getId(),
                idTime1,
                idTime2,
                jogo.getEsporte().toString(),
                jogo.getData()
        );
    }

    public static List<JogoDTO> toJogoDTOList(List<Jogo> jogos) {
        return jogos.stream()
                .map(jogo -> validateJogoAndCreateDTOList(jogo))
                .collect(Collectors.toList());
    }

    public static JogoDTO validateJogoAndCreateDTOList(Jogo jogo) {
        Long idTime1;
        Long idTime2;
        if(jogo.getTime1() == null) {
            idTime1 = null;
        } else {
            idTime1 = jogo.getTime1().getId();
        }
        if(jogo.getTime2() == null) {
            idTime2 = null;
        } else {
            idTime2 = jogo.getTime2().getId();
        }
        return new JogoDTO(jogo, idTime1, idTime2);
    }
}
