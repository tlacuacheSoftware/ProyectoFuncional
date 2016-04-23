

CREATE TABLE ALUMNO(
	id_Alumno SERIAL primary key,
	a_nombre character varying(32),
	a_contrasenha character varying(32) NOT NULL,
	a_correo character varying(100) NOT NULL
	
);
