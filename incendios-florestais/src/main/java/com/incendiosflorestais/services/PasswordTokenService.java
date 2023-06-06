package com.incendiosflorestais.services;

import com.incendiosflorestais.dto.PasswordTokenDTO;
import com.incendiosflorestais.models.PasswordToken;
import com.incendiosflorestais.models.User;
import com.incendiosflorestais.repositories.PasswordTokenRepository;
import com.incendiosflorestais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordTokenService {
    @Autowired
    PasswordTokenRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String create(String email) {
        Optional<UserDetails> userDetails = userRepository.findByEmail(email);
        if(userDetails.isPresent()) {
            User user = (User) userDetails.get();
            var passwordToken = new PasswordToken();
            passwordToken.setUser(user);
            passwordToken.setToken(UUID.randomUUID().toString());
            passwordToken.setExpiringDate(LocalDateTime.now().plusHours(4));
            repository.save(passwordToken);
            // ou retornar void e mandar um email com o token na url do form
            return passwordToken.getToken();
        }
        return "";
    }

    public boolean updatePassword(PasswordTokenDTO dto) {
        // valida token no banco
        var tokenOpt = repository.findByToken(dto.token());
        if(tokenOpt.isPresent()) {
            var token = tokenOpt.get();
            // valida se nao expirou
            if(token.getExpiringDate().isBefore(LocalDateTime.now())) {
                return false;
            }
            // validar senha
            User user = token.getUser();
            if(user.getPassword() == encoder.encode(dto.oldPassword())) {
                user.setPassword(encoder.encode(dto.newPassword()));
                userRepository.save(user);
            }
            return true;
        }
        return false;
    }
}
