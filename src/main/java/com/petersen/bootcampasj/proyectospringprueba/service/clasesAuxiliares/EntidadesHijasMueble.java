package com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Pedido;
import lombok.Data;

@Data
public class EntidadesHijasMueble {

    private Pedido pedido;
    private Color color;
    private Modelo modelo;
    private Estado estado;
}
