package com.portfolio.finalversion.repositorys;

import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    
}
