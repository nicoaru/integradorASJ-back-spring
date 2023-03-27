package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Consulta;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ConsultaServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/consultas")
@Api(value = "controller consultas", tags = "Consultas")
public class ConsultaController {
    private final ConsultaServiceInterface serviceConsulta;

    /** Constructor con DI **/
    ConsultaController(
            ConsultaServiceInterface serviceConsulta
    ) {
        this.serviceConsulta = serviceConsulta;
    }


    /** Endpoints **/
    /** Endpoints **/

    @PostMapping
    @ApiOperation("Enviar consulta")
    public ResponseEntity enviar(@RequestBody Consulta newConsulta){
        System.out.println("Entró en POST /consultas - enviar");
        try{
            Consulta consultaEnviada = serviceConsulta.enviar(newConsulta);
            return new ResponseEntity(consultaEnviada, HttpStatus.CREATED);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

