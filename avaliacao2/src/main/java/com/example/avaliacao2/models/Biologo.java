package com.example.avaliacao2.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Biologo extends Usuario {
    private String universidade;
    private String companhia;
    private Integer codigo;
}
