package com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO;

import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.auxDto.Cliente_PedidoResponse;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.auxDto.Mueble_PedidoResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PedidoResponseDTO {

    Integer id;
    String direccionEntrega;
    String notas;
    Date fechaEntrada;
    Date fechaEntrega;
    Cliente_PedidoResponse cliente;
    List<Mueble_PedidoResponse> muebles;


}
