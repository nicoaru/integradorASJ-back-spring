package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.EstadoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/estados")
public class EstadoController {
    private final EstadoServiceInterface serviceEstado;

    /** Constructor con DI **/
    EstadoController(EstadoServiceInterface serviceEstado) {
        this.serviceEstado = serviceEstado;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAll(){

        return serviceEstado.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /estados/{id} - getById");

        return serviceEstado.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Estado newEstado){

        return serviceEstado.create(newEstado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        return serviceEstado.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody Estado updatedEstado){
        System.out.println("Entró en PUT /usuarios/{id} - updateById");

        if(updatedEstado.getNombre() == null) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("El nombre no puede ser nulo", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        else return serviceEstado.updateById(id, updatedEstado);
    }


}
