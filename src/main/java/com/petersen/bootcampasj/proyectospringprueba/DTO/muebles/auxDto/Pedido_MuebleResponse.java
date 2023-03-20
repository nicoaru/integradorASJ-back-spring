package com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.auxDto;

import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.auxDto.Mueble_PedidoResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Pedido_MuebleResponse {
    Integer id;
    String direccionEntrega;
    String notas;
    Date fechaEntrada;
    Date fechaEntrega;
    Cliente_Pedido_MuebleResponse cliente;
    List<Mueble_PedidoResponse> muebles;
}
