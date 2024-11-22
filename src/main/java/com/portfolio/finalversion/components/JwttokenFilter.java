package com.portfolio.finalversion.components;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.portfolio.finalversion.services.servicesi.UserServiceInterface;
import com.portfolio.finalversion.services.servicesimpl.JWTService;
import com.portfolio.finalversion.services.servicesimpl.UserServiceImplement;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwttokenFilter implements WebFilter{

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserServiceImplement userServiceImplement;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String token = obtenerJwtDePeticion(request);

        if (token != null && jwtService.validarToken(token)) {
            log.info("Token válido encontrado");
            String alias = jwtService.obtenerAliasDeJWT(token);

            return userServiceImplement.findByUsername(alias).flatMap(userDetails -> {
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                Mono<SecurityContext> securityContext = Mono.just(new SecurityContextImpl(auth));
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withSecurityContext(securityContext));
            });
        }

        log.info("No se encontró token o no es válido");
        return chain.filter(exchange); // Continuar si no hay token o no es válido
    }
    
    private String obtenerJwtDePeticion(ServerHttpRequest request){
        log.info("entra a obtener token");
        String bearerToken = request.getHeaders().getFirst("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
