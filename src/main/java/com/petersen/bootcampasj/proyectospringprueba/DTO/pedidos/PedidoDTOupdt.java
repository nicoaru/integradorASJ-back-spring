package com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PedidoDTOupdt {
    Integer id;
    String direccionEntrega;
    String notas;
    Date fechaEntrada;
    Date fechaEntrega;
    @NotNull
    Cliente cliente;
}
