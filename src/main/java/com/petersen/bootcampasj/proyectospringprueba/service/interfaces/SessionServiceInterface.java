package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.http.ResponseEntity;

public interface SessionServiceInterface {

    ResponseEntity login(Usuario credenciales);
    ResponseEntity logout(Usuario credenciales);

}
