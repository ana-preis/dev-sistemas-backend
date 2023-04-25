package com.example.avaliacao2.repositories;

import com.example.avaliacao2.models.Biologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface BiologoRepository extends JpaRepository<Biologo, Integer> {
    ArrayList<Biologo> findAllByCodigoAndUniversidade(Integer codigo, String universidade);
}
