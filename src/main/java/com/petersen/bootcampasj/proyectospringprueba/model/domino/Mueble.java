package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="muebles")
public class Mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Integer id;
    Integer largo;
    Integer alto;
    Integer profundidad;
    Integer cantidad;
    @Lob
    String notas;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_pedido")
    Pedido pedido;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_color")
    Color color;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_modelo")
    Modelo modelo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_estado")
    Estado estado;


    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/

    public Mueble(Integer id, Integer largo, Integer alto, Integer profundidad, Integer cantidad, String notas, Pedido pedido, Color color, Modelo modelo, Estado estado) {
        this.id = id;
        this.largo = largo;
        this.alto = alto;
        this.profundidad = profundidad;
        this.cantidad = cantidad;
        this.notas = notas;
        this.pedido = pedido;
        this.color = color;
        this.modelo = modelo;
        this.estado = estado;
    }
}