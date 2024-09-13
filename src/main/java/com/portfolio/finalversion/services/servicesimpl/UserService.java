package com.portfolio.finalversion.services.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.portfolio.finalversion.models.dtos.UserDTO;
import com.portfolio.finalversion.models.security.User;
import com.portfolio.finalversion.repositorys.UserRepository;
import com.portfolio.finalversion.services.servicesi.UserServiceI;

import java.util.List;
import java.util.Objects;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceI, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private RolService rolService;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByAlias(String alias){
        return userRepository.findByAlias(alias);
    }

    public User findById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public Long createOrUpdate(User user){
        return userRepository.save(user).getId();

    }

    public void update(User user){
        User userToUpdate = userRepository.findByAlias(user.getAlias());
        if (Objects.nonNull(userToUpdate)) {
            createOrUpdate(userToUpdate);
        }else{
            createOrUpdate(userToUpdate);
        }
    }

    public void disable(Long uuid){
        User userToDisable = findById(uuid);
        if(Objects.nonNull(userToDisable)){
            userToDisable.setActivo(false);
            createOrUpdate(userToDisable);
        }
    }

    public Boolean validate(Long uuid){
        return Objects.nonNull(findById(uuid));
    }

    public Boolean validate(String alias){
        return Objects.nonNull(findByAlias(alias));
    }

    public User findByToken(String auth){
        String token = auth.split(" ")[1];
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson json = new Gson();
        String data = json.fromJson(payload, String.class);
        String alias = data.valueOf("user_alias");
        return findByAlias(alias);
        
    }

    public Boolean isAdmin(User user){
        return rolService.isAdmin(user);
    }

    public UserDTO getUserDto(User user){
        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByAlias(username);
        List<GrantedAuthority> permisos = user.getRoles().stream()
                                            .map(
                                                role -> new SimpleGrantedAuthority(
                                                    role.getTipoRol().name())
                                            ).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getAlias(), user.getContrasena(),
                user.isActivo(),true,true,true,permisos);
    }

    
}
