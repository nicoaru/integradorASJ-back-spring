package com.petersen.bootcampasj.proyectospringprueba.customExceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Data
public class HttpClientErrorExceptionWithData extends HttpClientErrorException {
    private Object data;

    public HttpClientErrorExceptionWithData(HttpStatus statusCode, Object data) {
        super(statusCode);
        this.data = data;
    }

    public HttpClientErrorExceptionWithData(String message, HttpStatus statusCode, String statusText, Object data) {
        super(message, statusCode, statusText, null, null, null);
        this.data = data;
    }
}
