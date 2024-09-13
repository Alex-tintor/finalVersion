package com.portfolio.finalversion.repositories;

import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.User;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {

    User findByAlias(String alias);
    
}
