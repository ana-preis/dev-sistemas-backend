package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.ParkAddressDTO;
import com.incendiosflorestais.services.ParkAddressService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parks")
public class ParkAddressController {

    @Autowired
    private ParkAddressService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ParkAddressDTO> save(@RequestBody @Valid ParkAddressDTO dto) {
        ParkAddressDTO saved = service.save(dto);
        if(saved == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkAddressDTO> getByID(@PathVariable Long id) {
        ParkAddressDTO dto = service.getByID(id);
        if(dto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ParkAddressDTO>> search(@RequestParam(required = false) String city,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String state) {
        List<ParkAddressDTO> parks = service.search(city, state, name);
        return new ResponseEntity<>(parks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ParkAddressDTO> update(@PathVariable Long id,
                                                 @RequestBody @Valid ParkAddressDTO dto) {
        ParkAddressDTO updated = service.update(id, dto);
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
