package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;

public interface SessionServiceInterface {

    Usuario login(Usuario credenciales) throws HttpClientErrorExceptionWithData;
    boolean logout();

}
