package com.portfolio.finalversion.models.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.portfolio.finalversion.models.enums.RoleEnum;


import lombok.Data;


@Data
@Table(name = "Rol")
public class Rol {
    
    @Id
    private Long rUuid;

    private RoleEnum tipoRol;

    public Rol() {
    }

    public Rol(Long rUuid, RoleEnum tipoRol) {
        this.rUuid = rUuid;
        this.tipoRol = tipoRol;
    }

    @Override
    public String toString() {
        return "{" +
            " rUuid='" + getRUuid() + "'" +
            ", tipoRol='" + getTipoRol() + "'" +
            "}";
    }

}
