package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ModeloServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/modelos")
@Api(value = "controller modelos", tags = "Modelos")
public class ModeloController {
    private final ModeloServiceInterface serviceModelo;

    /** Constructor con DI **/
    ModeloController(ModeloServiceInterface serviceModelo) {
        this.serviceModelo = serviceModelo;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    @ApiOperation(value = "Traer todos los Modelos")
    public ResponseEntity getAll(){
        try {
            List<Modelo> modelos = serviceModelo.getAll();
            return new ResponseEntity<List<Modelo>>(modelos, HttpStatus.OK);
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
        System.out.println("Entró en /modelos/{id} - getById");
        try {
            Modelo modelo = serviceModelo.getById(id);
            return new ResponseEntity<Modelo>(modelo, HttpStatus.OK);
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
