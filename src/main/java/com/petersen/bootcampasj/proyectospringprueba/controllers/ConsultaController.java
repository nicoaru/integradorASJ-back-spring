package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteListDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.ClienteMapper;
import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Consulta;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ClienteServiceInterface;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ConsultaServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/consultas")
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


/*
@GetMapping
    public ResponseEntity getAll(){
       return serviceCliente.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /clientes/{id} - getById");

        return serviceCliente.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ClienteDTOcreate newCliente, BindingResult validationResult){

        // Validación de datos
        if(validationResult.hasErrors()){
            Map<String, String> validaciones = new HashMap<>();
            validationResult.getFieldErrors().forEach(fieldError -> {
                validaciones.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Datos inválidos", validaciones);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        return serviceCliente.create(newCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        return serviceCliente.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @Valid @RequestBody ClienteDTOupdt updatedCliente, BindingResult validationResult){
        System.out.println("Entró en PUT /clientes/{id} - updateById");

        // Validación de datos
        if(validationResult.hasErrors()){
            Map<String, String> validaciones = new HashMap<>();
            validationResult.getFieldErrors().forEach(fieldError -> {
                validaciones.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Datos inválidos", validaciones);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        return serviceCliente.updateById(id, updatedCliente);
    }
*/