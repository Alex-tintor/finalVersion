package com.portfolio.finalversion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.finalversion.services.servicesi.HealtInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/healt")
public class HealtController {
    
    @Autowired
    private HealtInterface healtInterface;

    @GetMapping("")
    public String serviciosVivos() {
        return healtInterface.healtlLine(); 
    }
    
}
