package com.example.avaliacao2.repositories;

import com.example.avaliacao2.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}
