package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.ImageDTO;
import com.incendiosflorestais.services.ImageService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ImageDTO> save(@RequestBody @Valid ImageDTO dto) {
        ImageDTO saved = service.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getByID(@PathVariable Long id) {
        ImageDTO dto = service.getByID(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(@RequestParam(required = false) String fileName,
                                                 @RequestParam(required = false)
                                                    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime date) {
        List<ImageDTO> images = service.search(fileName, date);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ImageDTO> update(@PathVariable Long id,
                                                 @RequestBody @Valid ImageDTO dto) {
        ImageDTO updated = service.update(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
