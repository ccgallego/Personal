/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.personal.service.impl;

import com.utp.personal.data.entity.UsuarioEntity;
import com.utp.personal.data.repository.UsuarioRepository;
import com.utp.personal.service.UsuarioService;
import com.utp.personal.web.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nn
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    //Declaramos variable de tipo UsuarioRepository para la persistencia con la BD
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Declaramos variable de mapper, para mapear los datos DTO a entity
    @Autowired
    private ModelMapper mapper;

    //Metodo guardar Usuario, recibe como parametro una variable tipo UsuarioDTO
    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("El usuario debe contener la información completa");
        }
        //Declaramos variable tipo UsuarioEntity para almacenar lo que devuelve el metodo save del repositorio
        UsuarioEntity u = this.usuarioRepository.save(mapper.map(usuario, UsuarioEntity.class));
        //Volvemos a mapear la respuesta del save a DTO para retornar
        return mapper.map(u, UsuarioDTO.class);
        
    }

    //Metodo para listar todos los usuarios en la base de datos
    @Override
    public List<UsuarioDTO> listarUsuarios() {
        //Creamos lista vacia para guardar los usuario
        List<UsuarioDTO> listaUsuarios = new ArrayList<>();
        //Creamos variable tipo Iterable y le asignamos el resultado(usuarios) del metodo findAll del repositorio
        Iterable<UsuarioEntity> u = this.usuarioRepository.findAll();
        //Recorremos la variable Iterable(lista) mientras exista un siguiente
        while (u.iterator().hasNext()) {
            //Recorro cada usuario en el iterable
            for (UsuarioEntity user : u) {
                //Añado los usuarios a la nueva lista mappeandolos a DTO
                listaUsuarios.add(mapper.map(user, UsuarioDTO.class));
            }
            //Retorno la lista con los usuarios
            return listaUsuarios;
        }
        //si el iterable no tiene un siguiente entonces devuelvo la lista vacia
        return listaUsuarios;
    }

    //Metodo para buscar un usuario por su Id
    @Override
    public UsuarioDTO buscarPorId(Long id) throws Exception {
        //verificamos que el id no sea nulo
        if (id == null) {
            throw new Exception("El id es necesario para consultar");
        }
        //Consultamos el usuario con el id en la base de datos y lo guardamos en la variable opUser
        Optional<UsuarioEntity> opUser = this.usuarioRepository.findById(id);
        //Creamos variable tipo UsuarioEntity, si opUser existe lo guardamos en u y si no se lanza la exception
        UsuarioEntity u = opUser.orElseThrow(() -> new Exception("No existe el usuario"));

        //Retornamos el usuario mappeado a DTO
        return mapper.map(u, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO eliminarUsuario(Long id) throws Exception{
        //Verificamos que el id no sea nulo para poder consultar
        if(id == null){
            throw new Exception("El id es necesario para consultar");
        }
        //Consultamos el usuario con el id para verificar si existe en la base de datos
        Optional<UsuarioEntity> opUser = this.usuarioRepository.findById(id);
        //si existe lo guardamos en la variable u, si no existe se lanza la excepcion
        UsuarioEntity u = opUser.orElseThrow(() -> new Exception("No existe el usuario"));
        //Se aplica el metodo deleteById del repositorio para eliminarlo de la base de datos
        this.usuarioRepository.deleteById(id);
        
        //Retornamos la información del usuario elimnado
        return mapper.map(u, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO user) throws Exception{
        //Buscamos el usuario en la base de datos con el id
        Optional<UsuarioEntity> opUser = this.usuarioRepository.findById(id);
        //Si el usuario existe se guarda en la variable u, si no existe se lanza la excepcion
        UsuarioEntity u = opUser.orElseThrow(() -> new Exception("No existe el usuario"));
        //Capturamos el idUsuario y se lo ingremos a la nueva información
        user.setIdUsuario(u.getIdUsuario());
        //Guardamos el usuario con el metodo save del repositorio y la respuesta la almacenamos en 'us'
        UsuarioEntity us = this.usuarioRepository.save(mapper.map(user, UsuarioEntity.class));
        //Retornamos la informacion nueva del usuario
        return mapper.map(us, UsuarioDTO.class);
    }
    
}
