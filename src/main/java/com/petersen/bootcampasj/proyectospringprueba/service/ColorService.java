package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
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

    public List<Color> getAll() {
        List<Color> colors = (List<Color>) repository.findAll();
        return colors;
    }

    @Override
    public Color getById(Integer id) throws HttpClientErrorExceptionWithData {
        Color color = repository.findById(id)
                            .orElse(null);
        if(color != null){
            return color;
        }
        else {
            String errorMessage = "Not found";
            throw new HttpClientErrorExceptionWithData(errorMessage, HttpStatus.NOT_FOUND, "Not found", null);
        }
    }


}
