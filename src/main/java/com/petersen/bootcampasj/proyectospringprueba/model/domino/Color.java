package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "colores")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Integer id;
    @Column(unique = true)
    String nombre;
    @Column
    String hex_referencia;



    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/
    public Color(Integer id, String nombre, String hex_referencia) {
        this.id = id;
        this.nombre = nombre;
        this.hex_referencia = hex_referencia;
    }
}

