package com.petersen.bootcampasj.proyectospringprueba.DTO.clientes;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ClienteDTOupdt {

    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    @Email
    private String email;
    private String notas;
    private TipoCliente tipoCliente;
}
