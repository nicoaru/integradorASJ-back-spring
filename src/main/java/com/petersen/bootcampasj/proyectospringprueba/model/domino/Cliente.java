package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;
    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @Lob
    private String notas;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_tipo_cliente")
        private TipoCliente tipoCliente;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties("cliente")
    private List<Pedido> pedidos;



    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/
    public Cliente(Integer id, String nombre, String apellido, String telefono, String email, String notas, TipoCliente tipoCliente, List<Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.notas = notas;
        this.tipoCliente = tipoCliente;
        this.pedidos = new ArrayList<>();
    }
}