package com.example.avaliacao2.dtos;

import com.example.avaliacao2.models.Biologo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record BiologoDTO(
        String nome,
        String universidade,
        String companhia,
        Integer codigo

) {
    public BiologoDTO(Biologo biologo) {
        this(
                biologo.getNome(),
                biologo.getUniversidade(),
                biologo.getCompanhia(),
                biologo.getCodigo()
        );
    }

    public static List<BiologoDTO> toBiologoDTOList(ArrayList<Biologo> biologos) {
        return biologos.stream()
                .map(biologo -> new BiologoDTO(biologo))
                .collect(Collectors.toList());

    }
}
