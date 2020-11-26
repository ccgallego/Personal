/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  nn
 */

CREATE TABLE usuarios(
id_usuario serial,
nombre varchar(50),
cedula varchar(50),
genero varchar(50),
correo varchar(50),
direccion varchar(50),
cargo varchar(50),
estado boolean,
primary key(id_usuario)
);

