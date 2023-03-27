package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ModeloServiceInterface {
    List<Modelo> getAll();
    Modelo getById(Integer id) throws HttpClientErrorExceptionWithData;

}
