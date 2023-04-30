package com.incendiosflorestais.models;

import com.incendiosflorestais.dto.FireDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Fire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float latitude;
    private Float longitude;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne
    private User recordResponsible;
    @Enumerated(EnumType.STRING)
    private Identification identification;
    private String satelliteName;
    @ManyToOne
    private ParkAddress parkAddress;
    private LocalDateTime savedAt;
    @OneToMany(mappedBy = "fire")
    private List<Image> images = new ArrayList<Image>();

    public Fire(FireDTO dto, User recordResponsible, ParkAddress park) {
        this.latitude = dto.latitude();
        this.longitude = dto.longitude();
        this.start = dto.start();
        this.end = dto.end();
        this.recordResponsible = recordResponsible;
        this.identification = dto.identification() != null ? Identification.valueOf(dto.identification().toUpperCase()) : null;
        this.satelliteName = dto.satelliteName();
        this.parkAddress = park;
        this.savedAt = LocalDateTime.now();
    }
}
