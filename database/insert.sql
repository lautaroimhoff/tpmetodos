INSERT INTO sexo(
	idsexo, sexo)
	VALUES (1, 'Femenino'), (2, 'Masculino');

INSERT INTO nacionalidad(
	idnacionalidad, nacionalidad)
	VALUES (1, 'Argentina');

INSERT INTO tipodocumento(
	idtipodocumento, tipodocumento)
	VALUES (1, 'DNI'), (2, 'LC'), (3, 'LR');

INSERT INTO gruposanguineo(
	idgruposanguineo, gruposanguineo, factor)
	VALUES (1, 'O', '+'), (2, 'O', '-'),(3, 'A', '+'),(4, 'A', '-'),(5, 'B', '+'),(6, 'B', '-'),(7, 'AB', '+'),(8, 'AB', '-');

INSERT INTO claselicencia (idclaselicencia, claselicencia)	VALUES 	
			(1,'A'),(2,'B'),(3,'C'),(4,'D'),(5,'E'),(6,'F'),(7,'G');
            
INSERT INTO costolicencia (vigencia, claselicencia, precio) VALUES           
    		(1, 1, 20), (3, 1, 25), (4, 1, 30), (5, 1, 40),
    		(1, 2, 20), (3, 2, 25), (4, 2, 30), (5, 2, 40),
            (1, 3, 23), (3, 3, 30), (4, 3, 35), (5, 3, 47),
            (1, 4, 23), (3, 4, 30), (4, 4, 35), (5, 4, 47),
            (1, 5, 29), (3, 5, 39), (4, 5, 44), (5, 5, 59),
            (1, 6, 29), (3, 6, 39), (4, 6, 44), (5, 6, 59),
            (1, 7, 20), (3, 7, 25), (4, 7, 30), (5, 7, 40);

INSERT INTO categorialicencia(
	idcategorialicencia, categorialicencia)
	VALUES (1, 'Primera vez'), (2, 'Renovacion'), (3, 'Copia');

INSERT INTO usuario(
	idUsuario,nombreusuario, contrasena, nombre, apellido, fechanacimiento, idtipodocumento, numerodocumento, direccion,
	numerotelefono, email, permisosuperusuario, idsexo, idEmpleadoGestor, fechaGestion)VALUES 
	(1,'Martín Destéfanis', 'martin', 'destefanis', 'martin', '1995-10-25', 1,'39124064', 'aca', '12345678', 'martin@hotmail.com',
	true, 1,1, '20/10/2018');