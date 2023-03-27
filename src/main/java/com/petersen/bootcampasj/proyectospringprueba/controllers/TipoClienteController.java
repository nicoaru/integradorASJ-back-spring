package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.TipoClienteServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tipos-cliente")
@Api(value = "controller tipos cliente", tags = "Tipos de cliente")
public class TipoClienteController {
    private final TipoClienteServiceInterface serviceTipoCliente;

    /** Constructor con DI **/
    TipoClienteController(TipoClienteServiceInterface serviceTipoCliente) {
        this.serviceTipoCliente = serviceTipoCliente;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    @ApiOperation(value = "Traer todos los tipos de cliente")
    public ResponseEntity getAll(){
        try {
            List<TipoCliente> tiposCliente = serviceTipoCliente.getAll();
            return new ResponseEntity<List<TipoCliente>>(tiposCliente, HttpStatus.OK);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity<HttpErrorResponseBody>(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity<HttpErrorResponseBody>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar por id")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /tiposCliente/{id} - getById");
        try {
            TipoCliente tipoCliente = serviceTipoCliente.getById(id);
            return new ResponseEntity<TipoCliente>(tipoCliente, HttpStatus.OK);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity<HttpErrorResponseBody>(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity<HttpErrorResponseBody>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
