package com.incendiosflorestais.controllers;

import com.incendiosflorestais.dto.RefreshTokenInputDTO;
import com.incendiosflorestais.dto.TokenInputDTO;
import com.incendiosflorestais.dto.TokenOutputDTO;
import com.incendiosflorestais.models.User;
import com.incendiosflorestais.services.RefreshTokenService;
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

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenOutputDTO> login(@Valid @RequestBody TokenInputDTO input) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                input.email(),
                input.password()
        );
        var authentication = manager.authenticate(authenticationToken);
        var user = (User) authentication.getPrincipal();
        var token = getToken(user);

        return new ResponseEntity<>(
                token,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenOutputDTO> refreshToken(@Valid @RequestBody RefreshTokenInputDTO dto) {
        var token = refreshTokenService.refreshTokenValid(dto.refreshToken());
        var user = token.getUser();
        var responseDto = getToken(user);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    private TokenOutputDTO getToken(User user) {
        var token = tokenService.gerarToken(user);
        var refreshToken = refreshTokenService.generateRefreshToken(user.getId(), token);
        return new TokenOutputDTO(token, refreshToken.getRefreshToken());
    }

}
