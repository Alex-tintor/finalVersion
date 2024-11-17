package com.portfolio.finalversion.services.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encodePass(String password){
        return encoder.encode(password);
    }

}
