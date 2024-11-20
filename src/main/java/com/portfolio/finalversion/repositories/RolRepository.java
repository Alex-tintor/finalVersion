package com.portfolio.finalversion.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.finalversion.models.security.Rol;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import com.portfolio.finalversion.models.enums.RoleEnum;


@Repository
public interface RolRepository extends R2dbcRepository<Rol,Long>{

    public Mono<Rol> findByTipoRol(RoleEnum tipoRol);

    @Query("SELECT r.* FROM Rol r " +
       "JOIN UserRoles ur ON r.rUuid = ur.urRolId " +
       "WHERE ur.urUserId = 1")
    Flux<Rol> findRolesByUserId();

}
