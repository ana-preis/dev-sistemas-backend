package com.example.demo.repositories;

import com.example.demo.models.Esporte;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findAllByEsporte(Esporte esporte);
    List<Jogo> findAllByTime1Is(Time time);
    List<Jogo> findAllByTime2Is(Time time);

}
