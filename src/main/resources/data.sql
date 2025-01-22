-- Insertar rol de administrador si no existe
INSERT INTO rol (id_rol, nombre)
SELECT 1, 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM rol WHERE id_rol = 1);

-- Ajustar la secuencia de rol
SELECT SETVAL(pg_get_serial_sequence('rol', 'id_rol'), COALESCE(MAX(id_rol), 1)) FROM rol;

-- Insertar usuario administrador si no existe
INSERT INTO usuario (id_usuario, nombre, apellido, documento, estado, email, password, id_rol)
SELECT 1, 'Admin', 'System', '12345678', true, 'admin@mail.com', '$2a$12$o6i2Z7FMuPD.7imsjRxI9.8XpuE/3fi7J.37viQbuuu0a8fIPn1ZS', 1
    WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE email = 'admin@mail.com');

-- Ajustar la secuencia de usuario
SELECT SETVAL(pg_get_serial_sequence('usuario', 'id_usuario'), COALESCE(MAX(id_usuario), 1)) FROM usuario;
