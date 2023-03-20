package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioServiceInterface {

    ResponseEntity getAll();
    ResponseEntity getById(Integer id);
    ResponseEntity create(Usuario newEntity);
    //ResponseEntity createMany(List<T> newEntitiesList);
    ResponseEntity updateById(Integer id, Usuario updatedEntity);
    ResponseEntity deleteById(Integer id);
    ResponseEntity getByUsername(String username);
}
