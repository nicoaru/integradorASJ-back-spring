package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ColorServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/colores")
public class ColorController {
    private final ColorServiceInterface serviceColor;

    /** Constructor con DI **/
    ColorController(ColorServiceInterface serviceColor) {
        this.serviceColor = serviceColor;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAll(){

        return serviceColor.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /clientes/{id} - getById");

        return serviceColor.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Color newColor){

        return serviceColor.create(newColor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        return serviceColor.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody com.petersen.bootcampasj.proyectospringprueba.model.domino.Color updatedColor){
        System.out.println("Entró en PUT /usuarios/{id} - updateById");

        if(updatedColor.getNombre() == null) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("El nombre no puede ser nulo", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        else return serviceColor.updateById(id, updatedColor);
    }

}
