package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.ColorJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ColorServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class ColorService implements ColorServiceInterface {
    private final ColorJPARepository repository;

    /** Constructor con DI **/
    public ColorService(ColorJPARepository repository) {
        this.repository = repository;
    }


    /** Métodos **/
    /** Métodos **/

    public ResponseEntity getAll() {
        try {
            List<Color> colors = (List<Color>) repository.findAll();
            return new ResponseEntity(colors, HttpStatus.OK);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getById(Integer id) {
        try {
            Color color = repository.findById(id)
                                .orElse(null);
            if(color != null){
                return new ResponseEntity(color, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Color no encontrado", null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity create(Color newColor) {
        System.out.println("Entro en colorService.create");
        try {
            Color color = repository.save(newColor);
            return new ResponseEntity(color, HttpStatus.CREATED);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateById(Integer id, Color updatedColor) {
        try {
            Color color = repository.findById(id)
                                .orElse(null);

            if(color != null) {
                updatedColor.setId(id);

                Color newColor = repository.save(updatedColor);
                return new ResponseEntity(newColor, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Color con id %s", id), null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(true, err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity deleteById(Integer id) {
        try {
            Color color = repository.findById(id)
                                    .orElse(null);

            if(color != null) {
                repository.deleteById(id);
                return new ResponseEntity(color, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Color con id %s", id), null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(true, err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
