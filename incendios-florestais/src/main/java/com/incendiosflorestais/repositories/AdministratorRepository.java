package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
