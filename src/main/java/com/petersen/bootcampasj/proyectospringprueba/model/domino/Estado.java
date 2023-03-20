package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Integer id;
    @Column(unique = true)
    String nombre;

    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/
    public Estado(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
