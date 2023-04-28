package com.example.demo.models;

import com.example.demo.dtos.EstudanteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudante extends Usuario {

  @ManyToOne
  private Turma turma;
  @NotNull
  @Column(unique = true)
  private String matricula;

  public Estudante(EstudanteDTO dto) {
    super(dto.nome(), dto.username(), dto.senha(), dto.is_admin());
    this.matricula = dto.matricula();
  }




}
