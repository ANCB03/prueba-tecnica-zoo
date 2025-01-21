--rol de administrador
INSERT INTO rol (id_rol, nombre)
SELECT 1, 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM rol WHERE id_rol = 1);

--usuario admin
INSERT INTO usuario (id_usuario, nombre, apellido, documento, estado, email, password, id_rol)
SELECT 1, 'Admin', 'System', '12345678', true, 'admin@mail.com', '$2a$12$o6i2Z7FMuPD.7imsjRxI9.8XpuE/3fi7J.37viQbuuu0a8fIPn1ZS', 1
    WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE email = 'admin@mail.com');