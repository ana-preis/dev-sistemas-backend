package com.incendiosflorestais.services;

import com.incendiosflorestais.models.RefreshToken;
import com.incendiosflorestais.repositories.RefreshTokenRepository;
import com.incendiosflorestais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.refresh.token.expiration_min}")
    private String expirationMin;

    public RefreshToken generateRefreshToken(long userID, String accessToken) {
        var user = userRepository.findById(userID);
        var refreshToken = new RefreshToken();
        refreshToken.setUser(user.get());
        refreshToken.setAccessToken(accessToken);
        refreshToken.setExpirationDate(getDataExpiracao());
        //utilizar algoritmo para gerar dado unico para salvar token. aqui escolhemos uuid
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        repository.save(refreshToken);
        return refreshToken;
    }

    private LocalDateTime getDataExpiracao() {
        return LocalDateTime.now().plusMinutes(Integer.parseInt(expirationMin));
    }

    public RefreshToken refreshTokenValid(String refreshToken) {
        var token = repository.findByRefreshToken(refreshToken).orElseThrow();
        if(!token.getExpirationDate().isAfter(LocalDateTime.now())) {
            repository.deleteById(token.getId());
            throw new RuntimeException("Token de atualização inválido");
        }
        return token;
    }

}
