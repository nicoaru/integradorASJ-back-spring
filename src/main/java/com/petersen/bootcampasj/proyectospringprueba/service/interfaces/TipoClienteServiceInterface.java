package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoClienteServiceInterface {
    List<TipoCliente> getAll();
    TipoCliente getById(Integer id) throws HttpClientErrorExceptionWithData;

}
