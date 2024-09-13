CREATE TABLE usuario (
    uuid BIGINT PRIMARY KEY,                -- ID principal
    uNombre VARCHAR(255),                   -- Nombre del usuario
    uApellido VARCHAR(255),                 -- Apellido del usuario
    uAlias VARCHAR(255) UNIQUE NOT NULL,    -- Alias único
    uPassword VARCHAR(255),                 -- Contraseña
    uPermisos BIGINT,                       -- Referencia a la tabla Rol (FK)
    uActivo BOOLEAN,                        -- Estado activo
    uCreacion TIMESTAMP,                    -- Fecha de creación
    uModificacion TIMESTAMP                 -- Fecha de actualización
);

-- Relación ManyToMany entre User y Rol se maneja a través de una tabla intermedia
CREATE TABLE user_roles (
    user_id BIGINT,
    rol_id BIGINT,
    PRIMARY KEY (user_id, rol_id),
    FOREIGN KEY (user_id) REFERENCES usuario(uuid),
    FOREIGN KEY (rol_id) REFERENCES Rol(rUuid)
);

CREATE TABLE Rol (
    rUuid BIGINT PRIMARY KEY,                -- ID principal
    rTipo VARCHAR(10) UNIQUE                 -- Tipo de rol, columna única
);
