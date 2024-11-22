package com.portfolio.finalversion.services.servicesimpl;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.portfolio.finalversion.services.servicesi.JWTServicei;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class JWTService implements JWTServicei {

    @Value("${secret.key}")
    private String jwtSecret;

    @Value("${secret.expirationMis}")
    private Long jwtExpiration;
    
    public JWTService(){
        super();
    }

    @Override
    public String generarToken(Authentication auth) {
        log.warn("inicia generarToken(Authentication auth)");
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Date ahora = new Date();
        log.warn("AHORA {}",ahora);
        Date fechaDeExpiracion = new Date(ahora.getTime() + this.jwtExpiration);
        log.warn("fechaDeExpiracion {}",fechaDeExpiracion);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(fechaDeExpiracion)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)),SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String obtenerAliasDeJWT(String token) {
        log.warn("inicia obtenerAliasDeJWT(String token)");
        int bits  = jwtSecret.getBytes(StandardCharsets.UTF_8).length * 8 ;
        Claims  claims = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public Boolean validarToken(String token) {
        log.warn("inicia validarToken(String token)");
        log.warn("-0-0-0-0-0 token del validar {}", token);
        try {
            Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            log.error("falla validarToken(String token)");
            return false;
        }
    }
}
