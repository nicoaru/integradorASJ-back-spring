package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionServiceInterface serviceSession;

    /** Constructor con DI **/
    SessionController(SessionServiceInterface sessionService) {
        this.serviceSession = sessionService;
    }


    /** Endpoints **/
    /** Endpoints **/

    @PostMapping
    public ResponseEntity login(@RequestBody Usuario credenciales) {
        return serviceSession.login(credenciales);
    }
}
