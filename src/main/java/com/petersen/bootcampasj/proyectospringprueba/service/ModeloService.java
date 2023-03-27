package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.ModeloJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ModeloServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class ModeloService implements ModeloServiceInterface {
    private final ModeloJPARepository repository;

    /** Constructor con DI **/
    public ModeloService(ModeloJPARepository repository) {
        this.repository = repository;
    }


    /** Métodos **/
    /** Métodos **/

    public List<Modelo> getAll() {
        List<Modelo> modelos = (List<Modelo>) repository.findAll();
        return modelos;
    }

    @Override
    public Modelo getById(Integer id) throws HttpClientErrorExceptionWithData {
        Modelo modelo = repository.findById(id)
                .orElse(null);
        if(modelo != null){
            return modelo;
        }
        else {
            String errorMessage = "Not found";
            throw new HttpClientErrorExceptionWithData(errorMessage, HttpStatus.NOT_FOUND, "Not found", null);
        }
    }
}
