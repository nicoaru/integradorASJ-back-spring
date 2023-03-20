package com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoCreateDTO;

import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoCreateDTO.auxDto.Mueble_PedidoCreate;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class PedidoCreateDTO {

    String direccionEntrega;
    String notas;
    Date fechaEntrada;
    Date fechaEntrega;
    @NotNull
    Cliente cliente;
    List<Mueble_PedidoCreate> muebles;

}
