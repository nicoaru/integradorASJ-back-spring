package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Consulta;

public interface ConsultaServiceInterface {

    Consulta enviar(Consulta newConsulta) throws Exception;

}
