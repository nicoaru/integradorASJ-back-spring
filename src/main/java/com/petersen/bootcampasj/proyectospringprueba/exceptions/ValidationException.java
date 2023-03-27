package com.petersen.bootcampasj.proyectospringprueba.exceptions;

import lombok.Data;

import java.util.HashMap;

@Data
public class ValidationException extends Exception {
    private final HashMap<String, Object> data;

    public ValidationException(String message,HashMap<String, Object> data) {
        super(message);
        this.data = data;
    }

}
