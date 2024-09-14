package com.portfolio.finalversion.models.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class LogInDTO implements Serializable,Cloneable {

    private String alias;

    private String password;

    public LogInDTO(){}

    public LogInDTO(String alias, String password){
        this.alias = alias;
        this.password = password;
    }
    
}
