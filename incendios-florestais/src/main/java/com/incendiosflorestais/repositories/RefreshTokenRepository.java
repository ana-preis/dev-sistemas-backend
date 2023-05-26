package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.RefreshToken;
import com.incendiosflorestais.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccessToken(String accessToken);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    @Modifying
    void deleteByUser(User user);
}
