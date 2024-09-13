package com.portfolio.finalversion.repositorys;

import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    User findByAlias(String alias);
    
}
