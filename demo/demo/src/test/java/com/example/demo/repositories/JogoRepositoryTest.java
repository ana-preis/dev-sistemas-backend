package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Esporte;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JogoRepositoryTest {
  @Autowired
  JogoRepository jogoRepository;

  @Test
  public void it_should_save_jogo() {
    Time time1 = new Time();
    Time time2 = new Time();
    Jogo jogo = new Jogo();
    jogo.setTime1(time1);
    jogo.setTime2(time2);
    jogo.setData(LocalDateTime.now());
    jogo = jogoRepository.save(jogo);
    Jogo jogoSaved = jogoRepository.findById(jogo.getId()).get();
    assertEquals(time1, jogoSaved.getTime1());
    assertEquals(time2, jogoSaved.getTime2());
    assertEquals(jogo.getData(), jogoSaved.getData());
    assertNotNull(jogoSaved.getId());
  }

  @Test
  public void it_should_get_jogo_by_esporte() {
    for(Esporte esporte: Esporte.values()){
        Jogo jogo = new Jogo();
        jogo.setEsporte(esporte);
        jogoRepository.save(jogo);
    }
    Esporte esporte = Esporte.values()[0];
    var jogos = jogoRepository.findAllByEsporte(esporte);
    assertTrue(jogos.size() > 0);
  }
}
