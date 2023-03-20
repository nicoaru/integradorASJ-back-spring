package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import org.springframework.http.ResponseEntity;

public interface TipoClienteServiceInterface {
    ResponseEntity getAll();
    ResponseEntity getById(Integer id);
    ResponseEntity create(TipoCliente newEntity);
    //ResponseEntity createMany(List<T> newEntitiesList);
    ResponseEntity updateById(Integer id, TipoCliente updatedEntity);
    ResponseEntity deleteById(Integer id);
}
