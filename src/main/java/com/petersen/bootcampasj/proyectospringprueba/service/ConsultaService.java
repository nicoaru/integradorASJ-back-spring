package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.ClienteMapper;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Consulta;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.ClienteJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.MuebleJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.PedidoJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.TipoClienteJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasCliente;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ClienteServiceInterface;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ConsultaServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

