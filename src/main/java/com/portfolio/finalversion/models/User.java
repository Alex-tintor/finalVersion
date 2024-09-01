package com.portfolio.finalversion.models;

import java.util.Collection;
import java.util.List;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.finalversion.models.enums.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uId")
    private Integer id;

    @Column(name = "uNombre")
    private String nombre;

    @Column(name = "uApellido")
    private String apellido;

    @Column(name = "uAlias")
    private String alias;

    @Column(name = "uPassword")
    private String contrasena;

    @Column(name = "uRol")
    private RoleEnum rol;

    @Column(name = "uActivo")
    private boolean activo;


    public User() {
    }

    public User(Integer id, String nombre, String apellido, String alias, String contrasena, RoleEnum rol) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getUsername();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public RoleEnum getRol() {
        return this.rol;
    }

    public void setRol(RoleEnum rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", alias='" + getAlias() + "'" +
            ", contrasena='" + getContrasena() + "'" +
            ", rol='" + getRol() + "'" +
            "}";
    }

}
