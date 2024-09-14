package com.portfolio.finalversion.services.servicesimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.portfolio.finalversion.services.servicesi.HealtInterface;

@Service
public class HealtImplement implements HealtInterface {

    @Value("${healt.message}")
    private String healtMessage;

    @Override
    public String healtlLine() {
        return  healtMessage;
    }
    
}
