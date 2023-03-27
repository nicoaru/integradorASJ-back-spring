package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
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

    public List<TipoCliente> getAll() {
        List<TipoCliente> tipoClientes = (List<TipoCliente>) repository.findAll();
        return tipoClientes;
    }

    @Override
    public TipoCliente getById(Integer id) throws HttpClientErrorExceptionWithData {
        TipoCliente tipoCliente = repository.findById(id)
                .orElse(null);
        if(tipoCliente != null){
            return tipoCliente;
        }
        else {
            String errorMessage = "Not found";
            throw new HttpClientErrorExceptionWithData(errorMessage, HttpStatus.NOT_FOUND, "Not found", null);
        }
    }
}
