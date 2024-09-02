package com.portfolio.finalversion.models.security;

import java.util.Collection;
import java.util.List;
import java.util.Date;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @Column(name = "uPermisos")
    private Authorities permisos;

    @Column(name = "uActivo")
    private boolean activo;

    @Column(name = "uCreacion")
    private Date fechaCreacion;

    @Column(name = "uModificacion")
    private Date fechaActualizacion;


    public User() {
    }


    public User(Integer id, String nombre, String apellido, String alias, String contrasena, Authorities permisos, boolean activo, Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.contrasena = contrasena;
        this.permisos = permisos;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }
   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority(permisos.getRol().name()));
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

    public Authorities getPermisos() {
        return this.permisos;
    }

    public void setPermisos(Authorities permisos) {
        this.permisos = permisos;
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

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @PrePersist
    private void onCreate(){
        this.setFechaCreacion(new Date());
    }

    @PreUpdate
    private void onUpdate(){
        this.setFechaActualizacion(new Date());
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", alias='" + getAlias() + "'" +
            ", contrasena='" + getContrasena() + "'" +
            "}";
    }

}
