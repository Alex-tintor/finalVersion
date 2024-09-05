package com.portfolio.finalversion.services.servicesi;

import com.portfolio.finalversion.models.security.Rol;
import com.portfolio.finalversion.models.security.User;

public interface RolServiceI {
    
    public Rol findRole(Long id);

    public Boolean isAdmin(User user);
}
