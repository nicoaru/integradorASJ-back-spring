package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
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

    public List<Usuario> getAll() {
        List<Usuario> usuarios = (List<Usuario>) repository.findAll();
        return usuarios;
    }

    @Override
    public Usuario getById(Integer id) {
        Usuario usuario = repository.findById(id)
                            .orElse(null);
        if(usuario != null){
            return usuario;
        }
        else {
            String errorMessage = "Not found";
            throw new HttpClientErrorExceptionWithData(errorMessage, HttpStatus.NOT_FOUND, "Not found", null);
        }

    }

    @Override
    public Usuario getByUsername(String username) throws HttpClientErrorExceptionWithData {
        Usuario usuario = repository.findByUsername(username)
                .orElse(null);
        if(usuario != null){
           return usuario;
        }
        else {
            String errorMessage = "Not found";
            throw new HttpClientErrorExceptionWithData(errorMessage, HttpStatus.NOT_FOUND, "Not found", null);
        }
    }

}
