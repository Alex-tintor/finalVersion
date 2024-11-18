package com.portfolio.finalversion.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.finalversion.models.dtos.LogInDTO;
import com.portfolio.finalversion.services.servicesimpl.JWTService;
import com.sun.tools.javac.Main;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth")
@Slf4j
public class LogInController {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final JWTService jwtService;

    public LogInController(ReactiveAuthenticationManager authenticationManager, JWTService jwtService){
        this.reactiveAuthenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    
    @PostMapping("/login")
    public Mono<ResponseEntity<JwtAuthenticationResponse>> login(@RequestBody LogInDTO logInDTO) {

        logger.info("inicia public Mono<ResponseEntity<JwtAuthenticationResponse>> login(@RequestBody LogInDTO logInDTO)");

        return reactiveAuthenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(logInDTO.getAlias(), logInDTO.getPassword()))
            .map(auth ->{
                String token = jwtService.generarToken(auth);
                return ResponseEntity.ok(new JwtAuthenticationResponse(token));
            })
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    public record LoginRequest(String alias, String password) {
    }

    public static class JwtAuthenticationResponse{
    
        private final String token;

        public JwtAuthenticationResponse(String token){
            this.token = token;
        }
        
    }
    
}