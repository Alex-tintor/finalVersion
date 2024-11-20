package com.portfolio.finalversion.models.security;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private final String token;

    public JwtAuthenticationResponse(String token){
        this.token = token;
    }
}
