package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioServiceInterface {

    List<Usuario> getAll();
    Usuario getById(Integer id) throws HttpClientErrorExceptionWithData;
    Usuario getByUsername(String username) throws HttpClientErrorExceptionWithData;
}
