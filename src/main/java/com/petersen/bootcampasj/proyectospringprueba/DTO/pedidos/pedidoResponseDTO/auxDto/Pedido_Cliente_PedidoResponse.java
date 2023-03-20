package com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.auxDto;

import lombok.Data;

import java.util.Date;

@Data
public class Pedido_Cliente_PedidoResponse {
    Integer id;
    String direccionEntrega;
    String notas;
    Date fechaEntrada;
    Date fechaEntrega;
}
