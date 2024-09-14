package com.portfolio.finalversion.repositories;

import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.User;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {

    Mono<User> findByAlias(String alias);
    
}
