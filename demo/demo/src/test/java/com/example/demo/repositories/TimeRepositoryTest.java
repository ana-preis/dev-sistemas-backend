package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Esporte;
import com.example.demo.models.Estudante;
import com.example.demo.models.Time;
import com.example.demo.models.Turma;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TimeRepositoryTest {
  @Autowired
  TimeRepository timeRepository;

  @Autowired
  TurmaRepository turmaRepository;

  @Test
  public void it_should_save_time() {
    Time time = new Time();
    Estudante capitao = new Estudante();
    Turma turma = new Turma();
    time.setCapitao(capitao);
    time.setEsporte(Esporte.values()[0]);
    time.setNome("Time");
    time.setTurma(turma);
    time = timeRepository.save(time);

    Time timeSaved = timeRepository.findById(time.getId()).get();
    assertEquals(capitao, timeSaved.getCapitao());
    assertEquals(turma, time.getTurma());
    assertEquals("Time", time.getNome());
    assertEquals(Esporte.values()[0], time.getEsporte());
  }

  @Test
  public void it_should_get_time_by_turma_id() {
    Turma turma = turmaRepository.save(new Turma());
    Time time = new Time();
    time.setTurma(turma);
    timeRepository.save(time);
    var times = timeRepository.findAllByTurma_Id(turma.getId());
    assertTrue(times.size() > 0);
  }
}
