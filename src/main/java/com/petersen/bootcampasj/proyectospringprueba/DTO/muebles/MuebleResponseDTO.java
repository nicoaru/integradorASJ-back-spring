package com.petersen.bootcampasj.proyectospringprueba.DTO.muebles;

import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.auxDto.Pedido_MuebleResponse;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.PedidoResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Modelo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MuebleResponseDTO {
    Integer id;
    Integer largo;
    Integer alto;
    Integer profundidad;
    Integer cantidad;
    String notas;
    Pedido_MuebleResponse pedido;
    Color color;
    @NotNull
    Modelo modelo;
    Estado estado;
}
