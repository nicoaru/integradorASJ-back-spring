package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import org.springframework.http.ResponseEntity;

public interface ModeloServiceInterface {
    ResponseEntity getAll();
    ResponseEntity getById(Integer id);
    ResponseEntity create(Modelo newEntity);
    //ResponseEntity createMany(List<T> newEntitiesList);
    ResponseEntity updateById(Integer id, Modelo updatedEntity);
    ResponseEntity deleteById(Integer id);
}
