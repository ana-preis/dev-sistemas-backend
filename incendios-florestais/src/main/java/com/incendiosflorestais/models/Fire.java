package com.incendiosflorestais.models;

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
}
