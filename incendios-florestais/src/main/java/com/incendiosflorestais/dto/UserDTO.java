package com.incendiosflorestais.dto;

import com.incendiosflorestais.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public record UserDTO(

        Long id,
        String firstName,
        String lastName,
        String email
) {
        public UserDTO(User user) {
                this (
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                );
        }

        public static List<UserDTO> toUserDTOList(List<User> users) {
                return users.stream()
                        .map(UserDTO::new)
                        .collect(Collectors.toList());
        }
}
