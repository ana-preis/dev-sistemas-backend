package com.incendiosflorestais.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incendiosflorestais.models.Image;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ImageDTO(
        Long id,
        @NotEmpty
        String fileName,
        String type,
        @NotNull
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime date,
        @NotNull
        Long fireID
) {
    public ImageDTO(Image image) {
        this (
                image.getId(),
                image.getFileName(),
                image.getType().toString(),
                image.getDate(),
                image.getFire().getId()
        );
    }

    public static List<ImageDTO> toImageDTOList(List<Image> images) {
        return images.stream()
                .map(ImageDTO::new)
                .collect(Collectors.toList());
    }
}
