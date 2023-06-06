package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.PasswordTokenDTO;
import com.incendiosflorestais.services.PasswordTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordTokenController {

    @Autowired
    private PasswordTokenService service;

    @GetMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestParam String email) {
        var token = service.create(email);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid PasswordTokenDTO dto) {
        service.updatePassword(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
