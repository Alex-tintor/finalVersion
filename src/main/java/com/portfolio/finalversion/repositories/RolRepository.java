package com.portfolio.finalversion.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.Rol;

@Repository
public interface RolRepository extends R2dbcRepository<Rol,Long>{
    
}
