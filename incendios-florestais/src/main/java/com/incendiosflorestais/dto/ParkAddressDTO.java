package com.incendiosflorestais.dto;

import com.incendiosflorestais.models.ParkAddress;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public record ParkAddressDTO(
        Long id,
        @NotEmpty
        @Size(min = 3, message = "city should have at least 3 characters")
        String city,
        @NotEmpty
        @Size(min = 2, message = "state should have at least 2 characters")
        String state,
        @NotEmpty
        @Size(min = 3, message = "country should have at least 3 characters")
        String country,
        @NotEmpty
        @Size(min = 3, message = "parkName should have at least 3 characters")
        String parkName
) {
        public ParkAddressDTO(ParkAddress dto){
                this (
                        dto.getId(),
                        dto.getCity(),
                        dto.getState(),
                        dto.getCountry(),
                        dto.getParkName()
                );
        }

        public static List<ParkAddressDTO> toParkAddressDTOList(List<ParkAddress> parks) {
                return parks.stream()
                        .map(ParkAddressDTO::new)
                        .collect(Collectors.toList());
        }
}
