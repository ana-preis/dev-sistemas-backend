package com.incendiosflorestais.models;

import com.incendiosflorestais.dto.ImageDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private Identification type;
    private LocalDateTime date;
    @ManyToOne
    private Fire fire;

    public Image(ImageDTO dto, Fire fire) {
        this.fileName = dto.fileName();
        this.type = dto.type() != null ? Identification.valueOf(dto.type().toUpperCase()) : null;
        this.date = dto.date();
        this.fire = fire;
    }
}
