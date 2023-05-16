package com.incendiosflorestais.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.incendiosflorestais.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.expiration_min}")
    private Integer expirationMin;

    public String gerarToken(User usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT
                    .create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token.", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime
                .now()
                .plusMinutes(expirationMin)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        return verify(tokenJWT).getSubject();
    }

    public DecodedJWT verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT
                .require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
    }

}
