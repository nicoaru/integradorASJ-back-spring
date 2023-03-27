package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Consulta;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ConsultaServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class ConsultaService implements ConsultaServiceInterface {

    /** Constructor **/
    /** Constructor **/
    public ConsultaService() {}


    /** Métodos **/
    /** Métodos **/
    @Override
    public Consulta enviar(Consulta newConsulta) throws Exception {
        if(newConsulta.getNombre() != null && newConsulta.getTelefono() != null && newConsulta.getTextoConsulta() != null) {
            // aca iría la lógica para enviar la consulta por mail al administrador
            return newConsulta;
        }
        else {
            throw new HttpClientErrorExceptionWithData("Faltan datos", HttpStatus.BAD_REQUEST, "Bad request", null);
        }
    }
}

