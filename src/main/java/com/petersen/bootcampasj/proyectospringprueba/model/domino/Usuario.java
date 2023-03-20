package com.petersen.bootcampasj.proyectospringprueba.model.domino;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Integer id;
    @Column(unique = true)
    String username;
    @Column()
    String password;


    /*** CONSTRUCTOR ***/
    /*** CONSTRUCTOR ***/
    public Usuario(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}