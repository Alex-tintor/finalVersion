package com.portfolio.finalversion.models.security;

import org.springframework.data.relational.core.mapping.Table;

import com.portfolio.finalversion.models.enums.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Table(name = "Rol")
public class Rol {
    
    @Id
    @Column(name = "rUuid", length = 60)
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long rUuid;

    @Column(name = "rTipo", unique = true, length = 10)
    private RoleEnum tipoRol;
}
