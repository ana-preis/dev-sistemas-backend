package com.incendiosflorestais.models;

import com.incendiosflorestais.dto.AdministratorDTO;
import com.incendiosflorestais.dto.UserDTO;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Administrator extends User {

    private boolean isAdmin;

    public Administrator(AdministratorDTO adminDto, UserDTO dto, String token){
        super(dto.firstName(), dto.lastName(), dto.email());
        this.isAdmin = adminDto.isAdmin();
        super.setPassword(token);
    }
}
