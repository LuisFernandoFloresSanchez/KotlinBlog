create database examen;

use examen;

create table entradas(
	idEntrada int primary key auto_increment,
	titulo varchar(50),
	autor varchar(50),
	fecha varchar(50),
	contenido varchar(1000)
);