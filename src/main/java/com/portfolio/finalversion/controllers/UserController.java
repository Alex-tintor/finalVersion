package com.portfolio.finalversion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.finalversion.models.dtos.UserDTO;
import com.portfolio.finalversion.models.security.User;
import com.portfolio.finalversion.services.servicesi.RolServiceInterface;
import com.portfolio.finalversion.services.servicesi.UserServiceInterface;
import com.portfolio.finalversion.services.utils.ExcepcionPersonalizada;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserServiceInterface userService;

    @Autowired 
    private RolServiceInterface rolService;

    @PostMapping("/crear")
    public Mono<ResponseEntity<?>> crearUsuario(@RequestBody UserDTO userDTO) throws ExcepcionPersonalizada {
        User usuario = new User(userDTO);
    
        try{

            return userService.createOrUpdate(usuario)
                .flatMap(id -> {
                    if (id != null) {
                        return Mono.just(ResponseEntity.ok(usuario)); // Devolver el usuario creado
                    } else {
                        return Mono.just(ResponseEntity.badRequest().body("No fue posible crear el usuario")); // Devolver error de creación
                    }
                })
                .defaultIfEmpty(ResponseEntity.badRequest().body("No fue posible crear el usuario")) // Si el Mono está vacío
                .onErrorResume(e -> 
                    {
                        e.printStackTrace();
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error del servidor: " + e.getMessage()));
                    }); // Manejo de errores
        }catch(ExcepcionPersonalizada e){
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
        }
    }

    @GetMapping("/consultar/rol")
    public Mono<ResponseEntity<?>> consultarRol(@RequestParam(name = "id") Long idRol) {
        return rolService.findRole(idRol).flatMap(rol -> {
            if(rol.equals(null)){
                return Mono.just(ResponseEntity.badRequest().body("no hay roles con esa id"+ idRol));
            }else{
                return Mono.just(ResponseEntity.ok(rol));
            }
        });
    }
    
}
