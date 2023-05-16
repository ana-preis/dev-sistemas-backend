package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.TokenInputDTO;
import com.incendiosflorestais.dto.TokenOutputDTO;
import com.incendiosflorestais.models.User;
import com.incendiosflorestais.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenOutputDTO> login(@Valid @RequestBody TokenInputDTO input) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                input.email(),
                input.password()
        );
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((User) authentication.getPrincipal());
        var tokenResponse = new TokenOutputDTO(token);
        return new ResponseEntity<>(
                tokenResponse,
                HttpStatus.CREATED
        );
    }


}
