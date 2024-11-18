package com.portfolio.finalversion.services.servicesi;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.finalversion.models.dtos.UserDTO;
import com.portfolio.finalversion.models.security.User;
import com.portfolio.finalversion.services.utils.ExcepcionPersonalizada;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;;

public interface UserServiceInterface {

    public Flux<User> findAll();

    public Mono<User> findByAlias(String alias);

    public Mono<User> findById(Long userId);

    public Mono<Long> createOrUpdate(User user) throws ExcepcionPersonalizada;

    public void update(User user);

    public void disable(Long uuid);

    public Boolean validate(Long uuid);

    public Boolean validate(String alias);

    public Mono<User> findByToken(String token);

    public Boolean isAdmin(User user);

    public UserDTO getUserDto(User user);
    
}
