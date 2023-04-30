package com.example.demo.models;
import com.example.demo.dtos.TimeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Estudante> estudantes;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Esporte esporte;
    @OneToOne
    private Estudante capitao;
    @ManyToOne
    private Turma turma;

    public Time(TimeDTO dto, Estudante capitao, Turma turma) {
        this.nome = dto.nome();
        this.esporte = Esporte.valueOf(dto.esporte().toLowerCase());
        this.capitao = capitao;
        this.turma = turma;
    }
}
