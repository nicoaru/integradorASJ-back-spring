package com.petersen.bootcampasj.proyectospringprueba.DTO.clientes;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import lombok.Data;

@Data
public class ClienteListDTO {
    private Integer id;
    private String nombre;
    private String apellido;
}
