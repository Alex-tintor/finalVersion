package com.portfolio.finalversion.models.security;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("UserRoles")
public class UserRoles {
    
    @Column("urUserId")
    private Long userId;

    @Column("urRolId")
    private Long rolId;
}
