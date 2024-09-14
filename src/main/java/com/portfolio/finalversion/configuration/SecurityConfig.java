package com.portfolio.finalversion.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.portfolio.finalversion.components.JwttokenFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    // TODO esta mierda es lo que realiza la validacion
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwttokenFilter jwtTokenFilter) {
        log.info("entra filterChain");
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable) // Deshabilitamos CSRF porque trabajamos con tokens
            .authorizeExchange(auth -> 
                auth.pathMatchers("/healt", "/auth/login").permitAll() // Permitimos el acceso sin autenticación al login
                    .anyExchange().authenticated() // Requerimos autenticación para cualquier otra petición
            )
            .addFilterAt(jwtTokenFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Agregamos el filtro de JWT
            .build();
    }


    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authManager.setPasswordEncoder(passwordEncoder);
        return authManager;
    }


    @Bean PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
