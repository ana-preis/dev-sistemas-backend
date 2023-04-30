package com.example.demo.controllers;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.service.EstudanteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService service;

    @PostMapping()
    @Transactional
    public ResponseEntity<EstudanteDTO> save(@RequestBody @Valid EstudanteDTO dto) {
        EstudanteDTO newDto = service.save(dto);
        if(newDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudanteDTO> getByID(@PathVariable Long id) {
        EstudanteDTO dto = service.getByID(id);
        if(dto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<EstudanteDTO>> getAll(){
        List<EstudanteDTO> dtos = service.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudanteDTO> update(@PathVariable Long id,
                                               @RequestBody @Valid EstudanteDTO dto) {
        EstudanteDTO newDto = service.update(dto, id);
        if(newDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudanteDTO> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
