package com.example.demo.repositories;

import com.example.demo.models.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
}
