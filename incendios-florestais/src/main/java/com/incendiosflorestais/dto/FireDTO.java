package com.incendiosflorestais.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incendiosflorestais.models.Fire;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record FireDTO(
        Long id,
        @NotNull
        Float latitude,
        @NotNull
        Float longitude,
        @NotNull
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime start,
        @NotNull
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime end,
        LocalDateTime savedAt,
        @NotNull
        Long recordResponsibleID,
        Long parkID,
        String identification,
        String satelliteName
) {
        public FireDTO(Fire fire) {
                this(
                        fire.getId(),
                        fire.getLatitude(),
                        fire.getLongitude(),
                        fire.getStart(),
                        fire.getEnd(),
                        fire.getSavedAt(),
                        fire.getRecordResponsible() != null ? fire.getRecordResponsible().getId() : null,
                        fire.getParkAddress() != null ? fire.getParkAddress().getId() : null,
                        fire.getIdentification() != null ? fire.getIdentification().toString() : null,
                        fire.getSatelliteName() != null ? fire.getSatelliteName() : null
                );
        }

        public static List<FireDTO> toFireDTOList(List<Fire> fires) {
                return fires.stream()
                        .map(FireDTO::new)
                        .collect(Collectors.toList());
        }
}
