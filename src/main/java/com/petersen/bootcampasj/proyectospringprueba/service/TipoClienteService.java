package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.TipoClienteJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.TipoClienteServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class TipoClienteService implements TipoClienteServiceInterface {
    private final TipoClienteJPARepository repository;

    /** Constructor con DI **/
    public TipoClienteService(TipoClienteJPARepository repository) {
        this.repository = repository;
    }


    /** Métodos **/
    /** Métodos **/

    public ResponseEntity getAll() {
        try {
            List<TipoCliente> tipoClientes = (List<TipoCliente>) repository.findAll();
            return new ResponseEntity(tipoClientes, HttpStatus.OK);
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
            TipoCliente tipoCliente = repository.findById(id)
                                .orElse(null);
            if(tipoCliente != null){
                return new ResponseEntity(tipoCliente, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("TipoCliente no encontrado", null);
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
    public ResponseEntity create(TipoCliente newTipoCliente) {
        try {
            TipoCliente tipoCliente = repository.save(newTipoCliente);
            return new ResponseEntity(tipoCliente, HttpStatus.CREATED);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateById(Integer id, TipoCliente updatedTipoCliente) {
        try {
            TipoCliente tipoCliente = repository.findById(id)
                                .orElse(null);

            if(tipoCliente != null) {
                updatedTipoCliente.setId(id);

                TipoCliente newTipoCliente = repository.save(updatedTipoCliente);
                return new ResponseEntity(newTipoCliente, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe TipoCliente con id %s", id), null);
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
            TipoCliente tipoCliente = repository.findById(id)
                                    .orElse(null);

            if(tipoCliente != null) {
                repository.deleteById(id);
                return new ResponseEntity(tipoCliente, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe TipoCliente con id %s", id), null);
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
