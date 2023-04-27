package com.example.demo.models;

import jakarta.persistence.*;
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
  private Time time1;
  @OneToOne
  private Time time2;
  private Integer placar_time1;
  private Integer placar_time2;
  @Enumerated(EnumType.STRING)
  private Esporte esporte;
  private LocalDateTime data;
}
