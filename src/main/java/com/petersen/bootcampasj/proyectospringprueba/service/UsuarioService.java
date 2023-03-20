package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.UsuarioServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class UsuarioService implements UsuarioServiceInterface {
    private final UsuarioJPARepository repository;

    /** Constructor con DI **/
    public UsuarioService(UsuarioJPARepository repository) {
        this.repository = repository;
    }


    /** Métodos **/
    /** Métodos **/

    public ResponseEntity getAll() {
        try {
            List<Usuario> usuarios = (List<Usuario>) repository.findAll();
            return new ResponseEntity(usuarios, HttpStatus.OK);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getById(Integer id) {
        try {
            Usuario usuario = repository.findById(id)
                                .orElse(null);
            if(usuario != null){
                return new ResponseEntity(usuario, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Usuario no encontrado", null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getByUsername(String username) {
        try {
            Usuario usuario = repository.findByUsername(username)
                    .orElse(null);
            if(usuario != null){
                return new ResponseEntity(usuario, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Usuario no encontrado", null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity create(Usuario newUser) {
        try {
           boolean yaExisteUsername = repository.existsByUsername(newUser.getUsername());

            if (!yaExisteUsername) {
                Usuario usuario = repository.save(newUser);
                return new ResponseEntity(usuario, HttpStatus.CREATED);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("El nombre de usuario ya existe", null);
                return new ResponseEntity(errorBody, HttpStatus.CONFLICT);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateById(Integer id, Usuario updatedUser) {
        try {
            Usuario usuario = repository.findById(id)
                                .orElse(null);

            if(usuario != null) {
                updatedUser.setId(id);
/*
                usuario.setUsername(updatedUser.getUsername());
                usuario.setPassword(updatedUser.getPassword());
*/

                Usuario newUser = repository.save(updatedUser);
                return new ResponseEntity(newUser, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe usuario con id %s", id), null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(true, err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity deleteById(Integer id) {
        try {
            Usuario usuario = repository.findById(id)
                                    .orElse(null);

            if(usuario != null) {
                repository.deleteById(id);
                return new ResponseEntity(usuario, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe usuario con id %s", id), null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(true, err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
