CREATE SCHEMA died;

CREATE TABLE died.estacion (
id_estacion varchar(50) PRIMARY KEY,
nombre varchar(50),
horario_apertura varchar(10),
horario_cierre varchar(10),
estado varchar(50)
);

CREATE TABLE died.tarea_mantenimiento (
id_tarea varchar(50) PRIMARY KEY,
fecha_inicio date,
fecha_fin date,
observaciones varchar(200),
id_estacion varchar(50)
);

CREATE TABLE died.linea_transporte (
id_linea varchar(50) PRIMARY KEY,
nombre varchar(50),
color varchar(50),
estado varchar(50)
);

CREATE TABLE died.trayecto (
id_trayecto varchar(50) PRIMARY KEY,
distancia integer,
duracion integer,
cant_max_pasajeros integer,
estado varchar(50),
costo decimal(12,2),
id_linea varchar(50),
id_origen varchar(50),
id_destino varchar(50)
);

CREATE TABLE died.cliente (
id_cliente varchar(50) PRIMARY KEY,
nombre varchar(100),
email varchar(100)
);

CREATE TABLE died.camino (
id_camino varchar(50) PRIMARY KEY,
id_origen varchar(50),
id_destino varchar(50),
costo decimal(12,2),
duracion integer,
distancia integer
);

CREATE TABLE died.trayecto_camino (
id_trayecto varchar(50),
id_camino varchar (50),
CONSTRAINT pk_trayecto_camino PRIMARY KEY (id_trayecto, id_camino)
);


CREATE TABLE died.boleto (
id_boleto varchar(50) PRIMARY KEY,
fecha_venta date,
id_camino varchar(50),
id_origen varchar(50),
id_cliente varchar(50),
id_destino varchar(50)
);


-- Tabla Trayecto
ALTER TABLE died.trayecto 
ADD CONSTRAINT fk_linea FOREIGN KEY (id_linea) REFERENCES died.linea_transporte (id_linea) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.trayecto 
ADD CONSTRAINT fk_origen_trayecto FOREIGN KEY (id_origen) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.trayecto 
ADD CONSTRAINT fk_destino_trayecto FOREIGN KEY (id_destino) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

-- Tabla Tarea Mantenimiento  

ALTER TABLE died.tarea_mantenimiento 
ADD CONSTRAINT fk_estacion FOREIGN KEY (id_estacion) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

-- Tabla Boleto

ALTER TABLE died.boleto
ADD CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES died.cliente (id_cliente) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.boleto
ADD CONSTRAINT fk_camino FOREIGN KEY (id_camino) REFERENCES died.camino (id_camino) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.boleto 
ADD CONSTRAINT fk_origen_boleto FOREIGN KEY (id_origen) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.boleto 
ADD CONSTRAINT fk_destino_boleto FOREIGN KEY (id_destino) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

-- Tabla Trayecto-Camino

ALTER TABLE died.trayecto_camino
ADD CONSTRAINT fk_trayecto FOREIGN KEY (id_trayecto) REFERENCES died.trayecto (id_trayecto) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.trayecto_camino
ADD CONSTRAINT fk_camino_trayecto FOREIGN KEY (id_camino) REFERENCES died.camino (id_camino) ON DELETE RESTRICT ON UPDATE CASCADE;

-- Tabla Camino

ALTER TABLE died.camino 
ADD CONSTRAINT fk_origen_camino FOREIGN KEY (id_origen) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE died.camino 
ADD CONSTRAINT fk_destino_camino FOREIGN KEY (id_destino) REFERENCES died.estacion (id_estacion) ON DELETE RESTRICT ON UPDATE CASCADE;

-- Vista Ultimos_ID

CREATE OR REPLACE VIEW died.ultimos_id  AS (
SELECT *
FROM (SELECT CONCAT('EST', max(CAST(SUBSTR(id_estacion, 4) AS SIGNED))) as id_estacion
FROM died.estacion) as estacion, 
(SELECT CONCAT('BOL', max(CAST(SUBSTR(id_boleto, 4) AS SIGNED))) as id_boleto
FROM died.boleto) as boleto,
(SELECT CONCAT('PAT', max(CAST(SUBSTR(id_camino, 4) AS SIGNED))) as id_camino
FROM died.camino) as camino,
(SELECT CONCAT('LIN', max(CAST(SUBSTR(id_linea, 4) AS SIGNED))) as id_linea
FROM died.linea_transporte) as linea,
(SELECT CONCAT('MANT', max(CAST(SUBSTR(id_tarea, 5) AS SIGNED))) as id_tarea
FROM died.tarea_mantenimiento) as tarea,
(SELECT CONCAT('TRAY', max(CAST(SUBSTR(id_trayecto, 5) AS SIGNED))) as id_trayecto
FROM died.trayecto) as trayecto,
(SELECT CONCAT('CLI', max(CAST(SUBSTR(id_cliente, 4) AS SIGNED))) as id_cliente
FROM died.cliente) as cliente
);