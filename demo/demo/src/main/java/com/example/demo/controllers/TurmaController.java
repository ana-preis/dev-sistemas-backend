package com.example.demo.controllers;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.dtos.TurmaDTO;
import com.example.demo.models.Estudante;
import com.example.demo.models.Turma;
import com.example.demo.models.Turno;
import com.example.demo.repositories.TurmaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.dtos.TurmaDTO.toTurmaDTOList;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity<TurmaDTO> save(@RequestBody @Valid TurmaDTO dto) {
        Turma turma = new Turma(dto);
        Turma newTurma = repository.save(turma);
        if (newTurma == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        TurmaDTO newDto = new TurmaDTO(turma);
        return new ResponseEntity<>(newDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> getByID(@RequestParam Long id) {
        Optional<Turma> turma = repository.findById(id);
        if(turma.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        TurmaDTO dto = new TurmaDTO(turma.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Turma>> getAll() {
        List<Turma> turmas = repository.findAll();
        if(turmas.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TurmaDTO> update(@RequestParam Long id, @RequestBody TurmaDTO dto) {
        Optional<Turma> turma = repository.findById(id);
        if(turma.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Turma novaTurma = turma.get();
        novaTurma.setNome(dto.nome());
        novaTurma.setTurno(Turno.valueOf(dto.turno()));
        novaTurma.setSerie(dto.serie());
        Turma salvo = repository.save(novaTurma);
        TurmaDTO novoDto = new TurmaDTO(salvo);
        return new ResponseEntity<>(novoDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TurmaDTO> delete(@RequestParam Long id) {
        Optional<Turma> turma = repository.findById(id);
        if (turma.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        repository.delete(turma.get());
        return new ResponseEntity(null, HttpStatus.OK);
    }


}
