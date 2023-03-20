package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
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

    public ResponseEntity getAll() {
        try {
            List<Modelo> modelos = (List<Modelo>) repository.findAll();
            return new ResponseEntity(modelos, HttpStatus.OK);
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
            Modelo modelo = repository.findById(id)
                                .orElse(null);
            if(modelo != null){
                return new ResponseEntity(modelo, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Modelo no encontrado", null);
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
    public ResponseEntity create(Modelo newModelo) {
        try {
            Modelo modelo = repository.save(newModelo);
            return new ResponseEntity(modelo, HttpStatus.CREATED);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateById(Integer id, Modelo updatedModelo) {
        try {
            Modelo modelo = repository.findById(id)
                                .orElse(null);

            if(modelo != null) {
                updatedModelo.setId(id);

                Modelo newModelo = repository.save(updatedModelo);
                return new ResponseEntity(newModelo, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Modelo con id %s", id), null);
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
            Modelo modelo = repository.findById(id)
                                    .orElse(null);

            if(modelo != null) {
                repository.deleteById(id);
                return new ResponseEntity(modelo, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Modelo con id %s", id), null);
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
