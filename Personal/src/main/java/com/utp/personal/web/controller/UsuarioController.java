/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.personal.web.controller;

import com.utp.personal.service.UsuarioService;
import com.utp.personal.web.dto.UsuarioDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nn
 */
@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO user) throws Exception{
        UsuarioDTO usuario = service.guardarUsuario(user);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<?> getUsuarios() throws Exception{
        List<UsuarioDTO> usuario= service.listarUsuarios();
        if(!usuario.isEmpty()){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) throws Exception{
        if (id == null) {
            throw new Exception(("Debe ingresar el id del usuario a buscar"));
        }
        UsuarioDTO user = service.buscarPorId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws Exception{
        if(id == null){
            throw new Exception("No se puede eliminar");
        }
        UsuarioDTO user = service.eliminarUsuario(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UsuarioDTO user) throws Exception{
        if (id == null || user == null) {
            throw new Exception("Todos los parametros son necesarios");
        }
        UsuarioDTO u = this.service.actualizarUsuario(id, user);
        if (u != null) {
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.notFound().build();
    }
    
}
