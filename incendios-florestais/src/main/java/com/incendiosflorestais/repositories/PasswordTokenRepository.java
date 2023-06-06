package com.incendiosflorestais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.incendiosflorestais.models.PasswordToken;

import java.util.Optional;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
    Optional<PasswordToken> findByToken(String token);
}
