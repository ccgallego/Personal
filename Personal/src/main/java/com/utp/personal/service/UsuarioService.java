/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.personal.service;

import com.utp.personal.web.dto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author nn
 */
public interface UsuarioService {

    UsuarioDTO guardarUsuario(UsuarioDTO usuario) throws Exception;

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO buscarPorId(Long id) throws Exception;

    UsuarioDTO eliminarUsuario(Long id) throws Exception;

    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO user) throws Exception;
    
}
