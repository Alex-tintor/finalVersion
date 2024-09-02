package com.portfolio.finalversion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class LogInController {
    
    @GetMapping("/login")
    public String logIn() {
        return "logIn";
    }
    
    @GetMapping("/home")
    public String home() {
        return "home"; // Retorna la vista "home.html"
    }
}