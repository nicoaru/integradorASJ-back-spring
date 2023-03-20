package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ModeloServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/modelos")
public class ModeloController {
    private final ModeloServiceInterface serviceModelo;

    /** Constructor con DI **/
    ModeloController(ModeloServiceInterface serviceModelo) {
        this.serviceModelo = serviceModelo;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAll(){

        return serviceModelo.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /modelos/{id} - getById");

        return serviceModelo.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Modelo newModelo){

        return serviceModelo.create(newModelo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        return serviceModelo.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody Modelo updatedModelo){
        System.out.println("Entró en PUT /usuarios/{id} - updateById");

        if(updatedModelo.getNombre() == null) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("El nombre no puede ser nulo", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        else return serviceModelo.updateById(id, updatedModelo);
    }


}
