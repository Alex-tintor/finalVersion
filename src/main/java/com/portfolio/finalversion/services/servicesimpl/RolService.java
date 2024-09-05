package com.portfolio.finalversion.services.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.portfolio.finalversion.models.security.Rol;
import com.portfolio.finalversion.models.security.User;
import com.portfolio.finalversion.repositorys.RolRepository;
import com.portfolio.finalversion.services.servicesi.RolServiceI;

public class RolService implements RolServiceI {
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol findRole(Long id) {
        return rolRepository.findById(id).orElse(null);
    }


    @Override
    public Boolean isAdmin(User user) {
        return user.getRoles().contains(findRole(1L));
    }
}
