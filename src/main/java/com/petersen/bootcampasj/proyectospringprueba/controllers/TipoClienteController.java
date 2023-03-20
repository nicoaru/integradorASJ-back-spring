package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.TipoClienteServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tipos-cliente")
public class TipoClienteController {
    private final TipoClienteServiceInterface serviceTipoCliente;

    /** Constructor con DI **/
    TipoClienteController(TipoClienteServiceInterface serviceTipoCliente) {
        this.serviceTipoCliente = serviceTipoCliente;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAll(){

        return serviceTipoCliente.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /tipo_cliente/{id} - getById");

        return serviceTipoCliente.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TipoCliente newTipoCliente){

        return serviceTipoCliente.create(newTipoCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        return serviceTipoCliente.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody TipoCliente updatedTipoCliente){
        System.out.println("Entró en PUT /usuarios/{id} - updateById");

        if(updatedTipoCliente.getNombre() == null) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("El nombre no puede ser nulo", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        else return serviceTipoCliente.updateById(id, updatedTipoCliente);
    }

}
