package com.portfolio.finalversion.models.security;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.portfolio.finalversion.models.dtos.UserDTO;

import lombok.Data;

@Data
@Table(name = "usuario")
public class User{
    
    @Id
    private Long id;

    private String nombre;

    private String apellido;

    private String alias;

    private String contrasena;

    private boolean activo;

    private Date fechaCreacion;

    private Date fechaActualizacion;

    private List<Rol> roles;


    public User() {
    }

    public User(Long id, String nombre, String apellido, String alias, String contrasena, boolean activo, Date fechaCreacion, Date fechaActualizacion, List<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.contrasena = contrasena;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.roles = roles;
    }

    public User(UserDTO userDTO){
        this.activo = userDTO.isActivo();
        this.alias = userDTO.getAlias();
        this.apellido= userDTO.getApellido();
        this.contrasena = userDTO.getContrasena();
        this.nombre = userDTO.getNombre();
    }

    public void update(User data){
        setActivo(data.isActivo());
        setAlias(data.getAlias());
        setNombre(data.getNombre());
        setApellido(data.getApellido());
    }

    public void update(UserDTO data){
        setActivo(data.isActivo());
        setAlias(data.getAlias());
        setNombre(data.getNombre());
        setApellido(data.getApellido());
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", alias='" + getAlias() + "'" +
            ", contrasena='" + getContrasena() + "'" +
            ", activo='" + isActivo() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaActualizacion='" + getFechaActualizacion() + "'" +
            "}";
    }

}
