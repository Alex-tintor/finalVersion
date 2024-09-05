package com.portfolio.finalversion.models.security;

import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.finalversion.models.dtos.UserDTO;
import com.portfolio.finalversion.models.enums.RoleEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class User{
    
    @Id
    @Column(length = 60, name = "uuId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(name = "uNombre")
    private String nombre;

    @Column(name = "uApellido")
    private String apellido;

    @Column(name = "uAlias", unique = true, nullable = false)
    private String alias;

    @Column(name = "uPassword")
    private String contrasena;

    @Column(name = "uPermisos")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rol> roles = new ArrayList<>();

    @Column(name = "uActivo")
    private boolean activo;

    @Column(name = "uCreacion")
    private Date fechaCreacion;

    @Column(name = "uModificacion")
    private Date fechaActualizacion;


    public User() {
    }


    public User(Long id, String nombre, String apellido, String alias, String contrasena, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.contrasena = contrasena;
        // this.permisos = permisos;
        this.activo = activo;
    }

    public User(UserDTO userDTO){
        this.activo = userDTO.isActivo();
        this.alias = userDTO.getAlias();
        this.apellido= userDTO.getApellido();
        this.contrasena = userDTO.getContrasena();
        this.nombre = userDTO.getNombre();
        this.roles = userDTO.getRoles();
    }

    public void update(User data){
        setActivo(data.isActivo());
        setAlias(data.getAlias());
        setNombre(data.getNombre());
        setApellido(data.getApellido());
        setRoles(data.getRoles());
    }

    public void update(UserDTO data){
        setActivo(data.isActivo());
        setAlias(data.getAlias());
        setNombre(data.getNombre());
        setApellido(data.getApellido());
        setRoles(data.getRoles());
    }

    public void addRol(Rol rol){
        this.roles.add(rol);
    }

    public void removeRol(Rol rol){
        this.roles.remove(rol);
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
