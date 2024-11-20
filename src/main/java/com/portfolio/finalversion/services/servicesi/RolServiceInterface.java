package com.portfolio.finalversion.services.servicesi;

import org.springframework.stereotype.Service;

import com.portfolio.finalversion.models.enums.RoleEnum;
import com.portfolio.finalversion.models.security.Rol;
import com.portfolio.finalversion.models.security.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolServiceInterface {
    
    public Mono<Rol> findRole(Long id);

    public Boolean isAdmin(User user);

    public Mono<Rol> findRoleBytipo(RoleEnum role);
    public Flux<Rol> findRolesByUserId(Long userId);
}
