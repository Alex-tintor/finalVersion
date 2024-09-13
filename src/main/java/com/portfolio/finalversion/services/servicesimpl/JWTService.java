package com.portfolio.finalversion.services.servicesimpl;

import java.util.Date;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.finalversion.services.servicesi.JWTServicei;

import io.jsonwebtoken.Jwts;

public class JWTService implements JWTServicei {
    
    public JWTService(){
        super();
    }

    // @Override
    // public String generateToken(UserDetails userdetails){
    //     return Jwts.builder().setSubject(userdetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(getSingKey(), SignatureAlgorithm.HS256).compact();
    // }

    // @Override
    // public Key getSiginKey(){
    //     return new Key();
    // }
}
