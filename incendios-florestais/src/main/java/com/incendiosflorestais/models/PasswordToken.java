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
public class PasswordToken {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    // posso gerar uma variável contadora que bloqueia após X tetativas de atualizar a senha. daí não coloco o ManyToOne
    @ManyToOne
    private User user;
    private LocalDateTime expiringDate;
}
