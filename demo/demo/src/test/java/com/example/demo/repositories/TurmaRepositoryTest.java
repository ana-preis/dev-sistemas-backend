package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Esporte;
import com.example.demo.models.Turma;
import com.example.demo.models.Turno;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TurmaRepositoryTest {
  @Autowired
  TurmaRepository repository;

  @Test
  public void it_should_save_turma() {
    Turma turma = new Turma();
    turma.setNome("8B");
    turma.setSerie(8);
    turma.setTurno(Turno.values()[0]);
    Turma turmaSaved = repository.findById(turma.getId()).get();
    assertEquals("8B", turmaSaved.getNome());
    assertEquals(8, turmaSaved.getSerie());
    assertEquals(Turno.values()[0], turmaSaved.getTurno());
    assertNotNull(turmaSaved.getId());
  }
}
