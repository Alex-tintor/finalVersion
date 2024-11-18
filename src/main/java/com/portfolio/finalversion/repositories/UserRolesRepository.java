package com.portfolio.finalversion.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.UserRoles;

@Repository
public interface UserRolesRepository extends R2dbcRepository<UserRoles,Long>{
    
}
