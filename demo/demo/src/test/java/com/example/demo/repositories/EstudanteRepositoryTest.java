package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Esporte;
import com.example.demo.models.Estudante;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EstudanteRepositoryTest {
  @Autowired
  EstudanteRepository repository;

  @Test
  public void it_should_save_estudante() {
    Estudante estudante = new Estudante();
    estudante.setMatricula("teste");
    estudante.setNome("Estudante de Teste");
    estudante.setSenha("senha");
    Estudante estudanteSaved = repository.findById(estudante.getId()).get();
    assertEquals("teste", estudanteSaved.getMatricula());
    assertEquals("senha", estudanteSaved.getSenha());
    assertEquals("Estudante de Teste", estudanteSaved.getNome());
    assertNotNull(estudanteSaved.getId());
  }
}
