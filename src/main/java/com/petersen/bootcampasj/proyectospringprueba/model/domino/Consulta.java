package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Consulta {
    private String nombre;
    private String telefono;
    private String textoConsulta;
}