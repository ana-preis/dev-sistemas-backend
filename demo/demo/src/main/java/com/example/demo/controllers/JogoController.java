package com.example.demo.controllers;

import com.example.demo.dtos.JogoDTO;
import com.example.demo.service.JogoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService service;

    @PutMapping()
    @Transactional
    public ResponseEntity<JogoDTO> save(@RequestBody @Valid JogoDTO dto) {
        JogoDTO saved = service.save(dto);
        if(saved == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> getByID(@PathVariable Long id) {
        JogoDTO dto = service.getByID(id);
        if(dto == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<JogoDTO>> search(@RequestParam String esporte) {
        List<JogoDTO> dtos = service.search(esporte);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<JogoDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid JogoDTO dto) {
        JogoDTO updated = service.update(id, dto);
        if(updated == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
