package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tipos_cliente")
public class TipoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Integer id;
    @Column(unique = true)
    String nombre;




    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/

    public TipoCliente(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
