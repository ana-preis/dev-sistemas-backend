package com.example.avaliacao2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Biologo biologo;
    private Especie especie;
    private Date dataRegistro;
    private String localizacao;
    private MetodoColeta metodoDeColeta;
}
