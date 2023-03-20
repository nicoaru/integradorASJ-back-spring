package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
/* import com.proyectofinal.javaserver.util.DateHandler; */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Integer id;
    String direccionEntrega;
    @Lob
    String notas;
    Date fechaEntrada;
    Date fechaEntrega;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_cliente")
    //@JsonIgnoreProperties("pedidos")
    Cliente cliente;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    @JoinColumn(name = "id_pedido")
    @JsonIgnoreProperties("pedido")
    private List<Mueble> muebles;




    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/
    public Pedido(Integer id, String direccionEntrega, String notas, Date fechaEntrada, Date fechaEntrega, Cliente cliente, List<Mueble> muebles) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.notas = notas;
        this.fechaEntrada = fechaEntrada;
        this.fechaEntrega = fechaEntrega;
        this.cliente = cliente;
        //this.muebles = new ArrayList<>();
    }
}

