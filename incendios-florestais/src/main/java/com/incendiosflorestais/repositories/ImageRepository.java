package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByFileNameOrDate(String fileName, LocalDateTime date);
}
