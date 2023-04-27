package com.example.demo.models;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.repositories.TurmaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudante extends Usuario {

  @ManyToOne
  private Turma turma;
  private String matricula;

  public Estudante(EstudanteDTO dto, Turma turma) {
    this.turma = turma;
    this.matricula = dto.matricula();
  }




}
