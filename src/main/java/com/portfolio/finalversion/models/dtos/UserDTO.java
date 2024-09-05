package com.portfolio.finalversion.models.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.finalversion.models.security.Rol;
import com.portfolio.finalversion.models.security.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO implements Serializable{

    @NotEmpty
    @NotNull
    @NotBlank
    private String nombre;

    @NotEmpty
    @NotNull
    @NotBlank
    private String apellido;

    @NotEmpty
    @NotNull
    @NotBlank
    private String alias;

    @NotEmpty
    @NotNull
    @NotBlank
    private String contrasena;

    private List<Rol> roles = new ArrayList<>();

    private boolean activo;

    public UserDTO(){}

    public UserDTO(User user){
        this.activo = user.isActivo();
        this.alias = user.getAlias();
        this.apellido = user.getApellido();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getContrasena(){
        return contrasena;
    }

    private static final Long serialVersionUID = 1L;
    
}
