package com.portfolio.finalversion.models.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.portfolio.finalversion.models.enums.RoleEnum;


import lombok.Data;


@Data
@Table(name = "Rol")
public class Rol {
    
    @Id
    @Column("rUuid")
    private Long rUuid;

    @Column("rTipo")
    private RoleEnum tipoRol;
}
