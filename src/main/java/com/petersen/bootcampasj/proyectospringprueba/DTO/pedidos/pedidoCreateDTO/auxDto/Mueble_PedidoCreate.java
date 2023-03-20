package com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoCreateDTO.auxDto;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Mueble_PedidoCreate {

    Integer largo;
    Integer alto;
    Integer profundidad;
    Integer cantidad;
    String notas;
    Color color;
    @NotNull
    Modelo modelo;
    Estado estado;
}
