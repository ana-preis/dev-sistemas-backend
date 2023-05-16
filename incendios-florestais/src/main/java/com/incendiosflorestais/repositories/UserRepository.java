package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmailOrFirstName(String email, String firstName);
    Optional<UserDetails> findByEmail(String email);

}
