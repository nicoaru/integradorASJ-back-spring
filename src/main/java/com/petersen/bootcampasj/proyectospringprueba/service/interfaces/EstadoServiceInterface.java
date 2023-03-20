package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import org.springframework.http.ResponseEntity;

public interface EstadoServiceInterface {
    ResponseEntity getAll();
    ResponseEntity getById(Integer id);
    ResponseEntity create(Estado newEntity);
    //ResponseEntity createMany(List<T> newEntitiesList);
    ResponseEntity updateById(Integer id, Estado updatedEntity);
    ResponseEntity deleteById(Integer id);
}
