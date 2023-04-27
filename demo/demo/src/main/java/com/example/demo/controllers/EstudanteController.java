package com.example.demo.controllers;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.models.Estudante;
import com.example.demo.models.Turma;
import com.example.demo.repositories.EstudanteRepository;
import com.example.demo.repositories.TurmaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.dtos.EstudanteDTO.toEstudanteDTOList;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity<EstudanteDTO> save(@RequestBody @Valid EstudanteDTO dto) {
        Optional<Turma> turmaOpt = turmaRepository.findById(dto.idTurma());
        if(turmaOpt.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Estudante estudante = new Estudante(dto, turmaOpt.get());
        repository.save(estudante);
        EstudanteDTO newDto = new EstudanteDTO(estudante);
        if (newDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudanteDTO> getByID(@RequestParam Long id) {
        Optional<Estudante> estudante = repository.findById(id);
        if (estudante.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        EstudanteDTO dto = new EstudanteDTO(estudante.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Estudante>> getAll(){
        List<Estudante> estudantes = repository.findAll();
        if(estudantes.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudanteDTO> update(@RequestParam Long id, @RequestBody EstudanteDTO dto) {
        Optional<Estudante> estudante = repository.findById(id);
        if(estudante.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<Turma> turma = turmaRepository.findById(dto.idTurma());
        if(turma.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Estudante novoEstudante = estudante.get();
        novoEstudante.setMatricula(dto.matricula());
        novoEstudante.setTurma(turma.get());
        Estudante salvo = repository.save(novoEstudante);
        EstudanteDTO novoDto = new EstudanteDTO(salvo);
        return new ResponseEntity<>(novoDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudanteDTO> delete(@RequestParam Long id) {
        Optional<Estudante> estudante = repository.findById(id);
        if (estudante.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        repository.delete(estudante.get());
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
