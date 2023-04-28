package com.example.demo.models;

import com.example.demo.dtos.TurmaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @NotNull
    private Integer serie;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Turno turno;

    public Turma(TurmaDTO dto) {
        this.nome = dto.nome();
        this.serie = dto.serie();
        this.turno = Turno.valueOf(dto.turno());
    }

}
