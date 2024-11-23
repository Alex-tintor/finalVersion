package com.portfolio.finalversion.services.servicesi;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface JWTServicei {
 
    public String generarToken(Authentication auth);

}
