package com.portfolio.finalversion.services.servicesi;

import java.util.List;

import com.portfolio.finalversion.models.dtos.UserDTO;
import com.portfolio.finalversion.models.security.User;;

public interface UserServiceI {

    public List<User> findAll();

    public User findByEmail();

    public User findById();

    public Long create(User user);

    public void update(User user);

    public void disable(Long uuid);

    public Boolean validate(Long uuid);

    public Boolean validate(String alias);

    public User findByToken(String token);

    public Boolean isAdmin(User user);

    public UserDTO getUserDto(User user);
    
}
