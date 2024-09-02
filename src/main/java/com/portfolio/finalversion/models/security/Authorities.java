package com.portfolio.finalversion.models.security;

import org.springframework.data.relational.core.mapping.Table;

import com.portfolio.finalversion.models.enums.RoleEnum;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Table(name = "Autorizaciones")
public class Authorities {
    
    private Long id;

    private Long userId;

    private RoleEnum rol;

    

}
