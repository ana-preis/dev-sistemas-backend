package com.example.demo.dtos;

import com.example.demo.models.Estudante;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public record EstudanteDTO(
        Long id,
        @NotEmpty
        String nome,
        @NotEmpty
        @Email
        String username,
        @NotEmpty
        @Size(min = 6, max = 12, message = "Senha deve ter no mínimo 6 e no máximo 12 caracteres")
        String senha,
        Boolean is_admin,

        Long idTurma,
        @NotEmpty
        String matricula

) {
    public EstudanteDTO(Estudante estudante){
        this (
                estudante.getId(),
                estudante.getNome(),
                estudante.getUsername(),
                estudante.getSenha(),
                estudante.getIs_admin(),
                estudante.getTurma() != null ? estudante.getTurma().getId() : null,
                estudante.getMatricula()
        );
    }

    public static List<EstudanteDTO> toEstudanteDTOList(List<Estudante> estudantes) {
        return estudantes.stream()
                .map(estudante -> new EstudanteDTO(estudante))
                .collect(Collectors.toList());
    }

}
