package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ColorServiceInterface {

    List<Color> getAll();
    Color getById(Integer id) throws HttpClientErrorExceptionWithData;

}
