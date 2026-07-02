CREATE DATABASE IF NOT EXISTS banco;
USE banco;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario_nombre VARCHAR(30) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS personas (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    apellidos VARCHAR(30) NOT NULL,
    nombres VARCHAR(40) NOT NULL,
    cedula VARCHAR(20) NOT NULL,
    telefono INT NOT NULL,
    fecha_nacimiento DATE,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE IF NOT EXISTS cuentas (
    id_cuenta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    interes DECIMAL(3,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS cuentas_cliente (
    id_cuenta_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta),
    FOREIGN KEY (id_cliente) REFERENCES personas(id_cliente)
);

CREATE TABLE IF NOT EXISTS transacciones (
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(10,2) NOT NULL,
    tipo VARCHAR(40) NOT NULL,
    fecha DATETIME NOT NULL,
    id_cuenta_cliente INT NOT NULL,
    FOREIGN KEY (id_cuenta_cliente) REFERENCES cuentas_cliente(id_cuenta_cliente)
);

-- Seed: plan types for account registration
INSERT IGNORE INTO cuentas (id_cuenta, nombre, interes) VALUES
    (1, 'junior', 0.03),
    (2, 'clasica', 0.02),
    (3, 'senior', 0.04),
    (4, 'premium', 0.048);

-- Seed: service account placeholder for utility payments
-- The Java code references cuentas_cliente IDs 8-11 (Agua, Luz, Internet, Gas)
INSERT IGNORE INTO usuario (id_usuario, usuario_nombre, contrasena) VALUES (1, 'servicios', '');
INSERT IGNORE INTO personas (id_cliente, apellidos, nombres, cedula, telefono, id_usuario)
    VALUES (1, 'SERVICIOS', '', '9999999999', 0, 1);
INSERT IGNORE INTO cuentas_cliente (id_cuenta_cliente, id_cuenta, id_cliente) VALUES
    (8, 2, 1),
    (9, 2, 1),
    (10, 2, 1),
    (11, 2, 1);
