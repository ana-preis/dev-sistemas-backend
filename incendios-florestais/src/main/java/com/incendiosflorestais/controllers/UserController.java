package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.UserDTO;
import com.incendiosflorestais.dto.UserForm;
import com.incendiosflorestais.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping()
    @Transactional
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserForm dto) {
        UserDTO saved = service.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> search(@RequestParam(required = false) String email,
                                                @RequestParam(required = false) String name) {
        List<UserDTO> dtos = service.search(email, name);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getByID(@PathVariable Long id) {
        UserDTO dto = service.getByID(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid UserForm dto) {
        UserDTO updated = service.update(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
