package com.incendiosflorestais.infra.errors;

public class ValidationException extends RuntimeException{

    public ValidationException(String mensagem) {
        super(mensagem);
    }

}
