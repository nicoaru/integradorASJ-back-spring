package com.petersen.bootcampasj.proyectospringprueba;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpErrorResponseBody {
    boolean error = true;
    String message;
    Object data;

    public HttpErrorResponseBody(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
