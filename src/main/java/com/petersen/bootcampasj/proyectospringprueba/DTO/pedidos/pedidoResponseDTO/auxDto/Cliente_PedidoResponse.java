package com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.auxDto;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
public class Cliente_PedidoResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    @Email
    private String email;
    private String notas;
    private TipoCliente tipoCliente;
    private List<Pedido_Cliente_PedidoResponse> pedidos;
}
