package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import org.springframework.http.ResponseEntity;

public interface ColorServiceInterface {

    ResponseEntity getAll();
    ResponseEntity getById(Integer id);
    ResponseEntity create(Color newEntity);
    //ResponseEntity createMany(List<T> newEntitiesList);
    ResponseEntity updateById(Integer id, Color updatedEntity);
    ResponseEntity deleteById(Integer id);

}
