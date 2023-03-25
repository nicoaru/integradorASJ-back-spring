package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Consulta;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasCliente;

import java.util.HashMap;
import java.util.List;

public interface ConsultaServiceInterface {

    Consulta enviar(Consulta newConsulta) throws Exception;

}
