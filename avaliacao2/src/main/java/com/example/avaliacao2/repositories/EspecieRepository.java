package com.example.avaliacao2.repositories;

import com.example.avaliacao2.models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Integer> {
}
