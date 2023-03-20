package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class SessionService implements SessionServiceInterface {
    private final UsuarioJPARepository repositoryUsuario;

    /** Constructor con DI **/
    public SessionService(UsuarioJPARepository repository) {
        this.repositoryUsuario = repository;
    }


    /** Métodos **/
    /** Métodos **/

    @Override
    public ResponseEntity login(Usuario credenciales) {

        if(credenciales.getUsername() == null || credenciales.getPassword() == null) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Credenciales incompletas", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = repositoryUsuario.findByUsername(credenciales.getUsername())
                                .orElse(null);
        if(usuario == null || !usuario.getPassword().equals(credenciales.getPassword())) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Credenciales incorrectas", null);
            return new ResponseEntity(errorBody, HttpStatus.UNAUTHORIZED);
        }
        else return new ResponseEntity(usuario, HttpStatus.OK);

    }

    @Override
    public ResponseEntity logout(Usuario credenciales) {
        return null;
    }
}
