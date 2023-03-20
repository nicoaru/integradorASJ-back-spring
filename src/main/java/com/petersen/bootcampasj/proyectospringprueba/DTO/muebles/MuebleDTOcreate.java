package com.petersen.bootcampasj.proyectospringprueba.DTO.muebles;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Pedido;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MuebleDTOcreate {

    Integer largo;
    Integer alto;
    Integer profundidad;
    Integer cantidad;
    String notas;
    @NotNull
    Pedido pedido;
    Color color;
    @NotNull
    Modelo modelo;
    Estado estado;

}
