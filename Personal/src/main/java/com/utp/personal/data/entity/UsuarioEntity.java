/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.personal.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nn
 */

@Entity
@Table(name= "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String nombre;
    private String cedula;
    private String genero;
    private String fechaNacimiento;
    private String correo;
    private String direccion;
    private String fechaIngreso;
    private String cargo;
    private String usuario;
    private String password;
    private String rol;
    private Boolean estado;
    
    
    
}
