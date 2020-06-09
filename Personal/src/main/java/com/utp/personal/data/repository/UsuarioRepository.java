/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.personal.data.repository;

import com.utp.personal.data.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nn
 */
@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long>{
    
    UsuarioEntity findByUsuarioAndPassword(String usuario, String password);
    
}
