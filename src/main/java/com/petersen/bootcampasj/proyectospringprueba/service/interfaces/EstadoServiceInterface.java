package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstadoServiceInterface {
    List<Estado> getAll();
    Estado getById(Integer id) throws HttpClientErrorExceptionWithData;

}
