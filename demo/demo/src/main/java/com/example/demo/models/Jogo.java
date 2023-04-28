package com.example.demo.models;

import com.example.demo.dtos.JogoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne
  @NotNull
  private Time time1;
  @OneToOne
  @NotNull
  private Time time2;
  private Integer placar_time1;
  private Integer placar_time2;
  @Enumerated(EnumType.STRING)
  @NotNull
  private Esporte esporte;
  @NotNull
  private LocalDateTime data;

  public Jogo(JogoDTO dto, Time time1, Time time2) {
    this.time1 = time1;
    this.time2 = time2;
    this.data = dto.data();
    this.esporte = Esporte.valueOf(dto.esporte());
  }
}
