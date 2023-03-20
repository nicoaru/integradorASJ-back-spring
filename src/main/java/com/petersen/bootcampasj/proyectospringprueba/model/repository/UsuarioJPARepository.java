package com.petersen.bootcampasj.proyectospringprueba.model.repository;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioJPARepository extends PagingAndSortingRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);


}
