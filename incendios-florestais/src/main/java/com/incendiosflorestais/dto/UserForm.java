package com.incendiosflorestais.dto;

import com.incendiosflorestais.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public record UserForm(
        @NotEmpty
        @Size(min = 1, message = "first name should have at least one character")
        String firstName,
        @NotEmpty
        @Size(min = 1, message = "last name should have at least one character")
        String lastName,
        @NotEmpty
        @Size(min = 5, message = "email should have at least 5 characters")
        String email,
        @NotEmpty
        @Size(min = 6, max = 24, message = "password should have between 6 and 24 characters")
        String password
) {
    public UserForm(User user) {
        this (
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static List<UserForm> toUserDTOList(List<User> users) {
        return users.stream()
                .map(UserForm::new)
                .collect(Collectors.toList());
    }
}
