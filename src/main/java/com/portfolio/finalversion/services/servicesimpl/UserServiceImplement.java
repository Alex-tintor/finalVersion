package com.portfolio.finalversion.services.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.portfolio.finalversion.repositories.RolRepository;
import com.portfolio.finalversion.repositories.UserRepository;
import com.portfolio.finalversion.repositories.UserRolesRepository;
import com.portfolio.finalversion.services.servicesi.UserServiceInterface;
import com.shared_data.shared.models.User;
import com.shared_data.shared.models.UserRoles;
import com.shared_data.shared.models.dtos.UserDTO;
import com.shared_data.shared.utilities.ExcepcionPersonalizada;
import com.shared_data.shared.utilities.SecurityUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImplement implements UserServiceInterface, ReactiveUserDetailsService {

    @Autowired
    private RolServiceImplement rolService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findByAlias(String alias) {
        return userRepository.findByAlias(alias);
    }

    public Mono<User> findById(Long userId) {
        return userRepository.findById(userId).defaultIfEmpty(new User());
    }

    public Mono<Long> createOrUpdate(Mono<User> user) {        
        return userRepository.save(user.block()).map(User::getId);

    }

    public void update(User user) {
        Mono<User> userToUpdate = userRepository.findByAlias(user.getAlias());
        if (Objects.nonNull(userToUpdate)) {
            createOrUpdate(userToUpdate);
        } else {
            createOrUpdate(userToUpdate);
        }
    }

    public void disable(Long uuid) {
        Mono<User> userToDisable = findById(uuid);
        if (Objects.nonNull(userToDisable)) {
            userToDisable.map(user -> {
                user.setActivo(false);
                return user;
            });
            createOrUpdate(userToDisable);
        }
    }

    public Boolean validate(Long uuid) {
        return Objects.nonNull(findById(uuid));
    }

    public Boolean validate(String alias) {
        return Objects.nonNull(findByAlias(alias));
    }

    public Mono<User> findByToken(String auth) {
        String token = auth.split(" ")[1];
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson json = new Gson();
        String data = json.fromJson(payload, String.class);
        String alias = data.valueOf("user_alias");
        return findByAlias(alias);

    }

    public Boolean isAdmin(User user) {
        return rolService.isAdmin(user);
    }

    public UserDTO getUserDto(User user) {
        return new UserDTO(user);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.warn("Inicia método: findByUsername con username: {}", username);
    
        return userRepository.findByAlias(username)
                .doOnNext(user -> log.warn("Usuario encontrado: {}", user.getAlias()))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .flatMap(user -> {
                    log.warn("Cargando roles para el usuario con ID: {}", user.getId());
                    
                    return userRolesRepository.findByUserId(user.getId())
                            .flatMap(uRol -> rolService.findRole(uRol.getRolId())
                                    .map(rol -> {
                                        log.warn("Rol encontrado: {}", rol.getTipoRol());
                                        return new SimpleGrantedAuthority("ROLE_" + rol.getTipoRol().name());
                                    })
                            )
                            .collectList()
                            .doOnSuccess(authorities -> log.warn("Roles mapeados a autoridades: {}", authorities))
                            .map(authorities -> {
                                log.warn("Creando UserDetails para usuario: {}", user.getAlias());
                                return new org.springframework.security.core.userdetails.User(
                                        user.getAlias(),
                                        user.getContrasena(),
                                        authorities
                                );
                            });
                });
    }
}
