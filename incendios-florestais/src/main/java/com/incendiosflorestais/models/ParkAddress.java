package com.incendiosflorestais.models;

import com.incendiosflorestais.dto.ParkAddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ParkAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String state;
    private String country;
    private String parkName;
    @OneToMany(mappedBy = "parkAddress")
    private List<Fire> fireList;

    public ParkAddress(ParkAddressDTO dto) {
        this.city = dto.city();
        this.state = dto.state();
        this.country = dto.country();
        this.parkName = dto.parkName();
    }
}
