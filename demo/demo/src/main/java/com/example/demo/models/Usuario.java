package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nome;
  @Column(unique = true)
  private String username;
  private String senha;
  private Boolean is_admin = true;

  public Usuario(String nome, String username, String senha, Boolean is_admin) {
    this.nome = nome;
    this.username = username;
    this.senha = senha;
    this.is_admin = is_admin;
  }
}
