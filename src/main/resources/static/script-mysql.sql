CREATE DATABASE datasoft;

USE datasoft;

CREATE TABLE usuarios(
    idusr INTEGER(10) NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(10) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    clave VARCHAR(60) NOT NULL,
    PRIMARY KEY(idusr)
);

CREATE TABLE opciones_menu(
    idopc INTEGER(10) NOT NULL AUTO_INCREMENT,
    etiqueta VARCHAR(100) NOT NULL,
    comando VARCHAR(500) NOT NULL,
    estado VARCHAR(3) NOT NULL,
    PRIMARY KEY(idopc)
);

CREATE TABLE accesos_usuario(
    idacc INTEGER(10) NOT NULL AUTO_INCREMENT,
    idopc INTEGER(10) NOT NULL,
    idusr INTEGER(10) NOT NULL,
    PRIMARY KEY(idacc),
    CONSTRAINT fk_accesoss_opciones FOREIGN KEY(idopc) REFERENCES opciones_menu(idopc),
    CONSTRAINT fk_accessos_usuarios FOREIGN KEY(idusr) REFERENCES usuarios(idusr)
);

INSERT INTO usuarios(codigo,nombre,clave) VALUES('JPEREZ','Juan Pérez','$2a$10$MHTMNwrKx9EZeUS1pOU8X.dg9Ams0wl5vvHvBbPe1o11qmMf6j6eK');
INSERT INTO usuarios(codigo,nombre,clave) VALUES('MRECINOS','Mónica Recinos','$2a$10$tfkn0pMFaMCMJzI223UWSucwLY3Qr6QdKz6L4w5SZl9qz/QfHER6a');

INSERT INTO opciones_menu(etiqueta,comando,estado) VALUES('Mantenimiento de Usuarios','/mantenimiento-users','ACT');
INSERT INTO opciones_menu(etiqueta,comando,estado) VALUES('Mantenimiento de Opciones','/','INA');
INSERT INTO opciones_menu(etiqueta,comando,estado) VALUES('Mantenimiento de Roles','/','ACT');
INSERT INTO opciones_menu(etiqueta,comando,estado) VALUES('Mantenimiento de Permisos','/','ACT');

INSERT INTO accesos_usuario(idopc,idusr) VALUES(1,1);
INSERT INTO accesos_usuario(idopc,idusr) VALUES(2,1);
INSERT INTO accesos_usuario(idopc,idusr) VALUES(4,1);
INSERT INTO accesos_usuario(idopc,idusr) VALUES(1,2);
INSERT INTO accesos_usuario(idopc,idusr) VALUES(3,2);

CREATE USER datasoft IDENTIFIED BY 'Datasoft123!';
GRANT ALL PRIVILEGES ON datasoft.* TO datasoft;