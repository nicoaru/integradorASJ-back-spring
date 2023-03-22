package com.petersen.bootcampasj.proyectospringprueba.DTO.clientes;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ClienteSoloDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String notas;
    private TipoCliente tipoCliente;
}
