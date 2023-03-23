package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteListDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.ClienteMapper;
import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ClienteServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteServiceInterface serviceCliente;
    private final ClienteMapper mapperCliente;

    /** Constructor con DI **/
    ClienteController(
            ClienteServiceInterface serviceCliente,
            ClienteMapper mapperCliente) {
        this.serviceCliente = serviceCliente;
        this.mapperCliente = mapperCliente;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAll(){
        try{
            List<Cliente> muebles = serviceCliente.getAll();
            return new ResponseEntity(muebles, HttpStatus.OK);
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

    @GetMapping("/list")
    public ResponseEntity getList(){
        try{
            List<Cliente> muebles = serviceCliente.getAll();
            List<ClienteListDTO> mueblesDto = mapperCliente.listEntityToClienteListDto(muebles);
            return new ResponseEntity(muebles, HttpStatus.OK);
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

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /clientes/{id} - getById");
        try {
            Cliente cliente = serviceCliente.getById(id);

            return new ResponseEntity(cliente, HttpStatus.OK);

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

    @PostMapping
    public ResponseEntity create(@RequestBody ClienteDTOcreate newCliente){
        System.out.println("Entró en CREATE /clientes/{id} - updateById");
        try{
            Cliente newClienteEntidad = mapperCliente.dtoCreateToEntity(newCliente);

            Cliente cliente = serviceCliente.create(newClienteEntidad);
            return new ResponseEntity(cliente, HttpStatus.CREATED);
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

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody ClienteDTOupdt updateCliente){
        System.out.println("Entró en PUT /muebles/{id} - updateById");
        try{
            Cliente updatedClienteEntidad = mapperCliente.dtoUpdtToEntity(updateCliente);

            Cliente cliente = serviceCliente.updateById(id, updatedClienteEntidad);
            return new ResponseEntity(cliente, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        try{
            Cliente clienteEliminado = serviceCliente.deleteById(id);
            return new ResponseEntity(clienteEliminado, HttpStatus.OK);
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