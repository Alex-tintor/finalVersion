package com.portfolio.finalversion.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.finalversion.services.servicesi.HealtInterface;
import com.sun.tools.javac.Main;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/healt")
@Slf4j
public class HealtController {

    private static final Logger logger = LogManager.getLogger(Main.class);
    
    @Autowired
    private HealtInterface healtInterface;

    @GetMapping
    public String serviciosVivos() {
        logger.warn("inicia servicio public String serviciosVivos()");
        return healtInterface.healtlLine(); 
    }
    
}
