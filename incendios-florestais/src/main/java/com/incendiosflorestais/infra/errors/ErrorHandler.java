package com.incendiosflorestais.infra.errors;

import com.incendiosflorestais.services.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String email;

    @ExceptionHandler(
            {EntityNotFoundException.class, NoSuchElementException.class}
    )
    public ResponseEntity handleError404() {
        this.emailService.enviarEmail(email, "Novo erro 404", "Novo erro 404: ");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(ErrorFieldsDTO::new).toList();
        this.emailService.enviarEmail(email, "Novo erro 400", errors.toString());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(
            ValidationException.class
    )
    public ResponseEntity handleError400(ValidationException ex) {
        this.emailService.enviarEmail(email, "Novo erro 400 de validacao", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(
            InvalidDataAccessApiUsageException.class
    )
    public ResponseEntity handleError400(InvalidDataAccessApiUsageException ex) {
        this.emailService.enviarEmail(email, "Novo erro 400 de invalid data access", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
