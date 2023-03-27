package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.EstadoJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.EstadoServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class EstadoService implements EstadoServiceInterface {
    private final EstadoJPARepository repository;

    /** Constructor con DI **/
    public EstadoService(EstadoJPARepository repository) {
        this.repository = repository;
    }


    /** Métodos **/
    /** Métodos **/

    public List<Estado> getAll() {
        List<Estado> estados = (List<Estado>) repository.findAll();
        return estados;
    }

    @Override
    public Estado getById(Integer id) throws HttpClientErrorExceptionWithData {
        Estado estado = repository.findById(id)
                .orElse(null);
        if(estado != null){
            return estado;
        }
        else {
            String errorMessage = "Not found";
            throw new HttpClientErrorExceptionWithData(errorMessage, HttpStatus.NOT_FOUND, "Not found", null);
        }
    }

}
