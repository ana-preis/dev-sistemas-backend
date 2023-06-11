package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.FireDTO;
import com.incendiosflorestais.services.EmailService;
import com.incendiosflorestais.services.FireService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fires")
public class FireController {

    @Autowired
    private FireService service;

    @PostMapping
    @Transactional
    public ResponseEntity<FireDTO> save(@RequestBody FireDTO dto) {
        FireDTO saved = service.save(dto);
        log.info("New saved fire: " + saved.toString());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FireDTO> getByID(@PathVariable Long id) {
        FireDTO dto = service.getByID(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FireDTO>> search(@RequestParam(required = false) Float latitude,
                                                @RequestParam(required = false) Float longitude,
                                                @RequestParam(required = false)
                                                    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime  start,
                                                @RequestParam(required = false) String identification,
                                                @RequestParam(required = false) String city,
                                                @RequestParam(required = false) String state,
                                                @RequestParam(required = false) String parkName) {
        List<FireDTO> fires = service.search(latitude, longitude, start, identification, city, state, parkName);
        return new ResponseEntity<>(fires, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FireDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid FireDTO dto) {
        FireDTO updated = service.update(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
