package com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.auxDto;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class Cliente_Pedido_MuebleResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    @Email
    private String email;
    private String notas;
    private TipoCliente tipoCliente;
}
