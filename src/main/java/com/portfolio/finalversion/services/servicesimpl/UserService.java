package com.portfolio.finalversion.services.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.finalversion.models.dtos.UserDTO;
import com.portfolio.finalversion.models.security.User;
import com.portfolio.finalversion.repositorys.UserRepository;
import com.portfolio.finalversion.services.servicesi.UserServiceI;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService implements UserServiceI, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private RolService rolService;

    @Autowired
    private BCryptPasswordEncoder bPasswordEncoder;

    public List<User> findAll(){
        return new ArrayList<User>();
    }

    public User findByEmail(){
        return new User();
    }

    public User findById(){
        return new User();
    }

    public Long create(User user){
        return null;

    }

    public void update(User user){

    }

    public void disable(Long uuid){

    }

    public Boolean validate(Long uuid){
        return true;
    }

    public Boolean validate(String alias){
        return true;
    }

    public User findByToken(String token){
        return new User();
    }

    public Boolean isAdmin(User user){
        return true;
    }

    public UserDTO getUserDto(User user){
        return new UserDTO();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    
}
