CREATE TABLE ALUMNO(
	id_Alumno SERIAL primary key,
	s_nombre character varying(32) ,
	s_contrasenha character varying(32) ,
	s_correo character varying(100) unique
);

--select * from Alumno;
insert into ALUMNO (id_Alumno,s_nombre, s_contrasenha, s_correo )values(1,'rodrigo','12345','correo');
insert into ALUMNO (id_Alumno,s_nombre, s_contrasenha, s_correo )values(2,'valeria','12345','valeria@ciencias');

CREATE TABLE PROFESOR(
	id_Profesor SERIAL primary key,
	s_nombre character varying(32), --- nombre completo 
	s_contrasenha character varying(32) ,
	s_correo character varying(32) unique
);
--select * from PROFESOR;
insert into PROFESOR (id_Profesor,s_nombre, s_contrasenha, s_correo )values(1,'karim','12345','karim@ciencias');
insert into PROFESOR (id_Profesor,s_nombre, s_contrasenha, s_correo )values(2,'santigago','12345','santiago@ciencias');

---- el tipo lo debemos llenar antes de dar servicio debe estar lleno y poner nosotros el id  

CREATE TABLE TIPO(
	id_Tipo integer primary key,
	s_Tipo character varying(32)	
);

insert into tipo (id_Tipo,s_Tipo)values(1,'Actividad al Apoyo Docencia');
insert into tipo (id_Tipo,s_Tipo)values(2,'Actividad al Apoyo Divulgacion');
insert into tipo (id_Tipo,s_Tipo)values(3,'Actividad al Apoyo Investigacion');

--- la area la tenemos que llenar antes de dar el servicio y nososotro ponemos el id 
CREATE TABLE AREA(
	id_Area integer primary key ,
	s_Area character varying(32)
	
);
insert into area (id_Area,s_Area)values (1,'Computo Cientifico');
insert into area (id_Area,s_Area)values (2,'Redes Computadora');
insert into area (id_Area,s_Area)values (3,'Ingenieria Software Base Datos');
insert into area (id_Area,s_Area)values (4,'Inteligencia Artificial');
insert into area (id_Area,s_Area)values (5,'Imagenes Ambientes Virtuales');
insert into area (id_Area,s_Area)values (6,'Teoria Computacion');

CREATE TABLE ACTIVIDAD(
	id_Actividad SERIAL primary key  ,
	id_Profesor integer references PROFESOR (id_Profesor),
	id_Tipo integer references TIPO (id_tipo),
	id_Area integer references AREA(id_Area),
	i_CupoMaximo integer ,
	s_Descripciom character varying(128)
);
 insert into actividad (id_Actividad ,id_Profesor ,id_Tipo,id_Area,i_CupoMaximo,s_Descripciom)values(1,1,2,3,5,'gente para metricas');
 insert into actividad (id_Actividad ,id_Profesor ,id_Tipo,id_Area,i_CupoMaximo,s_Descripciom)values(2,1,1,1,3,'gente para automtas');
 
SELECT s_nombre from actividad natural join profesor natural join tipo natural join area;
CREATE TABLE SOLICITUD(
	id_Solicitud SERIAL primary key,
	id_Actividad integer references ACTIVIDAD(id_Actividad) ,
	id_Profesor integer references PROFESOR (id_Profesor) ,
	id_Alumno integer references ALUMNO (id_Alumno)
);

----SELECT * FROM SOLICITUD;
insert into SOLICITUD(id_Solicitud,id_Actividad ,id_Profesor ,id_Alumno )values(1,1,1,2);
insert into SOLICITUD(id_Solicitud,id_Actividad ,id_Profesor ,id_Alumno )values(2,1,1,1);

--select * from solicitud natural join area natural join alumno;
