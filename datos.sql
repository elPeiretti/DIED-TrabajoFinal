
-- Tabla Estacion
-- id, nombre, apertura, cierre, estado

INSERT INTO died.estacion 
VALUES ('EST1','Granja del Sol', '09:00', '20:00', 'OPERATIVA'),
('EST2','San Martin', '10:00', '21:00', 'OPERATIVA'),
('EST3','Bv. Galvez', '09:00', '20:00', 'OPERATIVA'),
('EST4','Big Ben', '11:00', '19:00', 'OPERATIVA'),
('EST5','Liberty', '09:00', '15:00', 'OPERATIVA'),
('EST6','Jolines', '09:00', '15:00', 'OPERATIVA'),
('EST7','Brigadier', '09:00', '15:00', 'OPERATIVA'),
('EST8','Albani', '09:00', '15:00', 'OPERATIVA'),
('EST9','Juanig', '09:00', '15:00', 'OPERATIVA'),
('EST10','Nacio', '09:00', '15:00', 'OPERATIVA'),
('EST11','Tomaspe', '09:00', '15:00', 'OPERATIVA'),
('EST12','Iretti', '09:00', '15:00', 'OPERATIVA'),
('EST13','Milanesa', '09:00', '15:00', 'OPERATIVA'),
('EST14','Choripan', '09:00', '15:00', 'EN_MANTENIMIENTO'),
('EST15','DIED', '09:00', '15:00', 'OPERATIVA'),
('EST16','TP2021', '09:00', '15:00', 'OPERATIVA'),
('EST17','Chicharron', '09:00', '15:00', 'OPERATIVA');


-- Tabla Tarea De Mantenimiento
-- id, inicio, fin, obs, estacionPerteneciente

INSERT INTO died.tarea_mantenimiento
VALUES ('MANT1','2021-01-15','2021-02-02', 'Pintado de bordes', 'EST1'),
('MANT2','2018-01-15','2019-02-02', 'Arreglo Puertas', 'EST2'),
('MANT3','2019-02-15','2021-03-10', 'Pavimentacion Vereda', 'EST2'),
('MANT4','2010-01-01','2021-02-02', 'Arreglo Luces', 'EST3'),
('MANT5','2021-01-15','2021-02-02', 'Arreglo Relojes', 'EST4'),
('MANT6','2021-01-15',null,null,'EST14'),
('MANT7','2021-01-17','2021-01-19','Observac1','EST5'),
('MANT8','2021-01-11','2021-01-20','Observac2','EST6'),
('MANT9','2021-01-30','2021-02-05','Observac3','EST7'),
('MANT10','2021-01-15','2021-02-05','Observac4','EST8'),
('MANT11','2021-01-11','2021-01-13','Observac5','EST9'),
('MANT12','2021-02-20','2021-04-16','Observac6','EST9'),
('MANT13','2021-05-15','2021-06-01','Observac7','EST9'),
('MANT14','2021-03-15','2021-04-19','Observac8','EST10'),
('MANT15','2021-01-01','2021-03-01','Observac9','EST11'),
('MANT16','2021-01-15','2021-01-16','Observac10','EST12'),
('MANT17','2016-05-01','2017-06-09','Observac11','EST13'),
('MANT18','2021-03-15','2021-07-03','Se cursa la materia','EST15'),
('MANT19','2021-07-15','2021-07-20','Se aprueba la materia','EST15'),
('MANT20','2021-07-20','2021-07-21','Se entrega el tp','EST16'),
('MANT21','2021-07-22','2021-07-23','Se aprueba el tp','EST16'),
('MANT22','2021-01-15','2021-01-16','Observac12','EST17'),
('MANT23','2021-01-30','2021-04-30','Observac13','EST17');

-- Tabla Linea de Transporte
-- id, nombre, color, estado

INSERT INTO died.linea_transporte 
VALUES ('LIN1','Linea Verde', '#33FF00', 'ACTIVA'),
('LIN2','Linea Bordo', '#990033', 'ACTIVA'),
('LIN3','Linea Amarilla', '#FFFF00', 'NO_ACTIVA'),
('LIN4','Linea Violeta', '#990099', 'ACTIVA'),
('LIN5','Fast Travel', '#FF9900', 'ACTIVA'),
('LIN6','Extravagante', '#FF0000', 'ACTIVA'),
('LIN7','Los Capos', '#FF00FF', 'ACTIVA'),
('LIN8','El Albanos', '#998800', 'ACTIVA'),
('LIN9','Aprobacion', '#00FFFF', 'ACTIVA');

-- Tabla Trayecto
-- id, dist, duracion, capacidad, estado, costo, id_linea, id_origen, id_destino

INSERT INTO died.trayecto 
VALUES ('TRAY1', 250, 120, 20, 'ACTIVO', 340, 'LIN1', 'EST5', 'EST1'),
('TRAY2', 150, 200, 30, 'ACTIVO', 250.8, 'LIN1', 'EST1', 'EST2'),
('TRAY3', 230, 210, 40, 'ACTIVO', 300, 'LIN2', 'EST1', 'EST4'),
('TRAY4', 300, 150, 25, 'ACTIVO', 140.5, 'LIN4', 'EST2', 'EST4'),
('TRAY5', 280, 140, 10, 'ACTIVO', 200, 'LIN4', 'EST4', 'EST3'),
('TRAY6', 510, 100, 45, 'ACTIVO', 1200, 'LIN5', 'EST7', 'EST6'),
('TRAY7', 92, 20, 25, 'ACTIVO', 400, 'LIN2', 'EST7', 'EST1'),
('TRAY8', 100, 30, 70, 'ACTIVO', 90.83, 'LIN1', 'EST7', 'EST5'),
('TRAY9', 50, 20, 60, 'INACTIVO', 130, 'LIN6', 'EST5', 'EST14'),
('TRAY10', 230, 115, 50, 'INACTIVO', 270.5, 'LIN6', 'EST14', 'EST12'),
('TRAY11', 200, 130, 30, 'ACTIVO', 410, 'LIN2', 'EST4', 'EST6'),
('TRAY12', 20, 5, 27, 'ACTIVO', 20, 'LIN7', 'EST3', 'EST11'),
('TRAY13', 30, 7, 27, 'ACTIVO', 10, 'LIN8', 'EST3', 'EST8'),
('TRAY14', 10, 5, 25, 'ACTIVO', 17, 'LIN7', 'EST11', 'EST12'),
('TRAY15', 92, 20, 20, 'ACTIVO', 25, 'LIN7', 'EST12', 'EST9'),
('TRAY16', 130, 50, 20, 'ACTIVO', 40, 'LIN8', 'EST8', 'EST9'),
('TRAY17', 15, 5, 37, 'ACTIVO', 90, 'LIN8', 'EST9', 'EST10'),
('TRAY18', 320, 100, 40, 'ACTIVO', 400, 'LIN9', 'EST9', 'EST6'),
('TRAY19', 250, 160, 60, 'ACTIVO', 110.2, 'LIN5', 'EST6', 'EST13'),
('TRAY20', 250, 100, 30, 'ACTIVO', 300, 'LIN9', 'EST6', 'EST13'),
('TRAY21', 580, 300, 110, 'ACTIVO', 730.3, 'LIN2', 'EST6', 'EST17'),
('TRAY22', 90, 100, 50, 'ACTIVO', 87.50, 'LIN5', 'EST13', 'EST15'),
('TRAY23', 100, 40, 30, 'ACTIVO', 300, 'LIN9', 'EST13', 'EST15'),
('TRAY24', 10, 10, 2, 'ACTIVO', 1000, 'LIN9', 'EST15', 'EST16');

-- Tabla Cliente
-- id, nombre, email
INSERT INTO died.cliente
VALUES ('CLI1','Esteban Quito','esteban@quito.com'),
('CLI2','Juan','juan@juan.com'),
('CLI3','Tometi','tomaspeiretti@gmail.com');

-- Tabla Camino
-- id, origen, destino, costo, durac, dist

INSERT INTO died.camino
VALUES ('PAT1','EST7','EST1',400,20,92),
('PAT2','EST15','EST16',1000,10,10),
('PAT3','EST7','EST3',900,460,602);

-- Tabla Boleto
-- id, fecha, camino, origen, cliente, destino

INSERT INTO died.boleto
VALUES ('BOL1','2021-07-19','PAT1','EST7','CLI1','EST1'),
('BOL2','2021-07-20','PAT2','EST15','CLI2','EST16'),
('BOL3','2021-07-21','PAT3','EST7','CLI3','EST3');

-- Tabla Trayecto_Camino
-- trayecto, camino
INSERT INTO died.trayecto_camino
VALUES ('TRAY7','PAT1'),
('TRAY24','PAT2'),
('TRAY7','PAT3'),
('TRAY3','PAT3'),
('TRAY5','PAT3');


