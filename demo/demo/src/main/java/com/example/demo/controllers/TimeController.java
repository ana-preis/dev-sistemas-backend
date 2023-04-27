package com.example.demo.controllers;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.dtos.TimeDTO;
import com.example.demo.models.Estudante;
import com.example.demo.models.Time;
import com.example.demo.models.Turma;
import com.example.demo.repositories.EstudanteRepository;
import com.example.demo.repositories.TimeRepository;
import com.example.demo.repositories.TurmaRepository;
import com.example.demo.service.TimeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeRepository repository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TimeService service;

    @PostMapping()
    @Transactional
    public ResponseEntity<TimeDTO> save(@RequestBody @Valid TimeDTO dto) {
        TimeDTO newDto = service.save(dto);
        if(newDto == null) {
            new ResponseEntity<>(service.save(dto), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }
}
