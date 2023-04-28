package com.example.demo.controllers;

import com.example.demo.dtos.TimeDTO;
import com.example.demo.service.TimeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/times")
public class TimeController {

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

    @GetMapping("/{id}")
    public ResponseEntity<TimeDTO> getByID(@PathVariable Long id) {
        TimeDTO dto = service.getByID(id);
        if(dto == null) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TimeDTO>> search(@RequestParam(required = false) Long idTurma) {
        List<TimeDTO> dtos = service.search(idTurma);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TimeDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid TimeDTO dto) {
        TimeDTO updated = service.update(id, dto);
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
