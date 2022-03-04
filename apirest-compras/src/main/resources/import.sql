INSERT INTO usuarios (username,password,enabled) VALUES ('patri','$2a$10$V0rIgL1NL5RsvxKqItwo6eCbtFjxrFrruk22f2X.ZQJyt7aMHjmZG',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('celia','$2a$10$g6ETKIr.0pFMyLRS0YKEAOX/iCLKaFFy3YwkUM./H1GWGnPKkdXta',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);

INSERT INTO compras (fecha,unidades) VALUES ('2022-03-03', '20');
INSERT INTO compras (fecha,unidades) VALUES ('2022-03-03', '2');
INSERT INTO compras (fecha,unidades) VALUES ('2022-03-03', '5');

INSERT INTO clientes(nombre,apellidos,empresa,puesto,provincia,cp,telefono,fecha_nacimiento,cod_compra) VALUES ('Patricia','Jerez Prado','Capgemini','Programador Junior','Asturias',33420,123456,'1975-07-15',1);
INSERT INTO clientes(nombre,apellidos,empresa,puesto,provincia,cp,telefono,fecha_nacimiento,cod_compra) VALUES ('Andrés','Gonzalez Fernández','Capgemini','Programador Junior','Asturias',33006,123456,'1994-05-24',2);
INSERT INTO clientes(nombre,apellidos,empresa,puesto,provincia,cp,telefono,fecha_nacimiento,cod_compra) VALUES ('David','Martínez LLano','Capgemini','Programador Junior','Asturias',33003,123456,'2001-03-9',3);
INSERT INTO clientes(nombre,apellidos,empresa,puesto,provincia,cp,telefono,fecha_nacimiento,cod_compra) VALUES ('Rafael','García I.','Capgemini','Programador Junior','Asturias',33499,123456,'1991-08-20',1);

INSERT INTO articulos (unidades_stock,stock_seguridad,imagen,descripcion,nombre,precio_unidad,cod_compra) VALUES (5,4,null,'Ratón con cable','Ratón',5.12,1);
INSERT INTO articulos (unidades_stock,stock_seguridad,imagen,descripcion,nombre,precio_unidad,cod_compra) VALUES (8,4,null,'Monitor PC gamer','Monitor Lenovo Plus++',789.99,2);
INSERT INTO articulos (unidades_stock,stock_seguridad,imagen,descripcion,nombre,precio_unidad,cod_compra) VALUES (25,31,null,'Libreta de cuadros','Lamela',7.80,3);
