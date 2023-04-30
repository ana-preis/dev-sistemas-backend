package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmailOrFirstName(String email, String firstName);

}
