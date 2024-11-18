package com.portfolio.finalversion.services.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.portfolio.finalversion.models.enums.RoleEnum;
import com.portfolio.finalversion.models.security.Rol;
import com.portfolio.finalversion.models.security.User;
import com.portfolio.finalversion.repositories.RolRepository;
import com.portfolio.finalversion.services.servicesi.RolServiceInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
// @DependsOn("rolRepository")
public class RolServiceImplement implements RolServiceInterface{
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Mono<Rol> findRole(Long id) {
        return rolRepository.findById(id).defaultIfEmpty(new Rol());
    }

    @Override
    public Boolean isAdmin(User user) {
        return user.getRoles().contains(findRole(1L));
    }

    @Override
    public Mono<Rol> findRoleBytipo(RoleEnum role) {
        return rolRepository.findByTipoRol(role).defaultIfEmpty(new Rol());
    }
}
