/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.personal.web.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    
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
