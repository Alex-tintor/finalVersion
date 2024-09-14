package com.portfolio.finalversion.services.servicesimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.portfolio.finalversion.services.servicesi.JWTServicei;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
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
        log.info("inicia generarToken(Authentication auth)");
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Date ahora = new Date();
        Date fechaDeExpiracion = new Date(ahora.getTime() + this.jwtExpiration);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(fechaDeExpiracion)
                .signWith(SignatureAlgorithm.ES512,jwtSecret)
                .compact();
    }

    @Override
    public String obtenerAliasDeJWT(String token) {
        log.info("inicia obtenerAliasDeJWT(String token)");
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public Boolean validarToken(String token) {
        log.info("inicia validarToken(String token)");
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            log.error("falla validarToken(String token)");
            return false;
        }
    }
}
