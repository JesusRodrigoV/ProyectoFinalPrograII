-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-06-11 20:07:37.746

-- tables
-- Table: cliente
CREATE TABLE cliente (
    id_cliente int  NOT NULL AUTO_INCREMENT,
    id_persona int  NOT NULL,
    cedula decimal(10,0)  NOT NULL,
    telefono decimal(8,0)  NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (id_cliente)
);

-- Table: cuentas
CREATE TABLE cuentas (
    id_cuenta int  NOT NULL AUTO_INCREMENT,
    nombre varchar(30)  NOT NULL,
    interes decimal(3,2)  NOT NULL,
    CONSTRAINT cuentas_pk PRIMARY KEY (id_cuenta)
);

-- Table: cuentas_cliente
CREATE TABLE cuentas_cliente (
    id_cuenta_cliente int  NOT NULL AUTO_INCREMENT,
    id_cuenta int  NOT NULL,
    id_cliente int  NOT NULL,
    CONSTRAINT cuentas_cliente_pk PRIMARY KEY (id_cuenta_cliente)
);

-- Table: personas
CREATE TABLE personas (
    id_persona int  NOT NULL AUTO_INCREMENT,
    apellidos varchar(30)  NOT NULL,
    nombres varchar(40)  NOT NULL,
    id_usuario int  NOT NULL,
    CONSTRAINT personas_pk PRIMARY KEY (id_persona)
);

-- Table: transacciones
CREATE TABLE transacciones (
    id_transaccion int  NOT NULL AUTO_INCREMENT,
    monto decimal(10,2)  NOT NULL,
    tipo varchar(40)  NOT NULL,
    fecha date  NOT NULL,
    id_cuenta_cliente int  NOT NULL,
    CONSTRAINT transacciones_pk PRIMARY KEY (id_transaccion)
);

-- Table: usuario
CREATE TABLE usuario (
    id_usuario int  NOT NULL AUTO_INCREMENT,
    usuario_nombre varchar(30)  NOT NULL,
    contrasena varchar(30)  NOT NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (id_usuario)
);

-- foreign keys
-- Reference: cliente_personas (table: cliente)
ALTER TABLE cliente ADD CONSTRAINT cliente_personas FOREIGN KEY cliente_personas (id_persona)
    REFERENCES personas (id_persona);

-- Reference: cuentas_cliente_cliente (table: cuentas_cliente)
ALTER TABLE cuentas_cliente ADD CONSTRAINT cuentas_cliente_cliente FOREIGN KEY cuentas_cliente_cliente (id_cliente)
    REFERENCES cliente (id_cliente);

-- Reference: cuentas_cliente_cuentas (table: cuentas_cliente)
ALTER TABLE cuentas_cliente ADD CONSTRAINT cuentas_cliente_cuentas FOREIGN KEY cuentas_cliente_cuentas (id_cuenta)
    REFERENCES cuentas (id_cuenta);

-- Reference: personas_usuario (table: personas)
ALTER TABLE personas ADD CONSTRAINT personas_usuario FOREIGN KEY personas_usuario (id_usuario)
    REFERENCES usuario (id_usuario);

-- Reference: transacciones_cuentas_cliente (table: transacciones)
ALTER TABLE transacciones ADD CONSTRAINT transacciones_cuentas_cliente FOREIGN KEY transacciones_cuentas_cliente (id_cuenta_cliente)
    REFERENCES cuentas_cliente (id_cuenta_cliente);

-- End of file.

