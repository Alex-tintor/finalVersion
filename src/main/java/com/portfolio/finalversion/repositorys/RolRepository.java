package com.portfolio.finalversion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long>, JpaSpecificationExecutor<Rol>{
    
}
