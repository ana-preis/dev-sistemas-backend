package com.example.demo.repositories;

import com.example.demo.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findAllByTurma_Id(Long id);
}
