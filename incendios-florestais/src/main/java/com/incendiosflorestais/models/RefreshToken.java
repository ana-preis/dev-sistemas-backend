package com.incendiosflorestais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String accessToken;
    @Column(unique = true)
    private String refreshToken;
    private LocalDateTime expirationDate;
    @OneToOne
    private User user;
}
