package com.example.demo.models;

import com.example.demo.dtos.TurmaDTO;
import jakarta.persistence.*;
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
    private Integer serie;
    @Enumerated(EnumType.ORDINAL)
    private Turno turno;

    public Turma(TurmaDTO dto) {
        this.nome = dto.nome();
        this.serie = dto.serie();
        this.turno = Turno.valueOf(dto.turno());
    }

}
