-- Verifica si la tabla 'usuario' no existe, y si no existe, créala
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usuario]') AND type in (N'U'))
BEGIN
    EXEC('CREATE TABLE usuario (
        uuid BIGINT IDENTITY(1,1) PRIMARY KEY, 
        uNombre VARCHAR(255), 
        uApellido VARCHAR(255), 
        uAlias VARCHAR(255) UNIQUE NOT NULL, 
        uPassword VARCHAR(255), 
        uActivo BIT, 
        uCreacion TIMESTAMP, 
        uModificacion DATETIME2
    )')
END;
-- Verifica si la tabla 'Rol' no existe, y si no existe, créala
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Rol]') AND type in (N'U'))
BEGIN
    EXEC('CREATE TABLE Rol (
        rUuid BIGINT PRIMARY KEY, 
        rTipo VARCHAR(10) UNIQUE
    )')
END;

-- Verifica si la tabla 'userRoles' no existe, y si no existe, créala
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[userRoles]') AND type in (N'U'))
BEGIN
    EXEC('CREATE TABLE userRoles (
        user_id BIGINT, 
        rol_id BIGINT, 
        PRIMARY KEY (user_id, rol_id), 
        FOREIGN KEY (user_id) REFERENCES usuario(uuid), 
        FOREIGN KEY (rol_id) REFERENCES Rol(rUuid)
    )')
END;

