package com.example.demo.controllers;

import com.example.demo.dtos.TurmaDTO;
import com.example.demo.service.TurmaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @PostMapping()
    @Transactional
    public ResponseEntity<TurmaDTO> save(@RequestBody @Valid TurmaDTO dto) {
        TurmaDTO newTurma = service.save(dto);
        if(newTurma == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newTurma, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> getByID(@PathVariable Long id) {
        TurmaDTO dto = service.getByID(id);
        if(dto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TurmaDTO>> getAll() {
        List<TurmaDTO> dtos = service.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TurmaDTO> update(@PathVariable Long id,
                                           @RequestBody @Valid TurmaDTO dto) {
        TurmaDTO newDto = service.update(id, dto);
        if(newDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TurmaDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
