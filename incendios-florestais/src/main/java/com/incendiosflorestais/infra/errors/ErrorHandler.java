package com.incendiosflorestais.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(
            {EntityNotFoundException.class, NoSuchElementException.class}
    )
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(ErrorFieldsDTO::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(
            ValidationException.class
    )
    public ResponseEntity handleError400(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(
            InvalidDataAccessApiUsageException.class
    )
    public ResponseEntity handleError400(InvalidDataAccessApiUsageException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
