package com.petersen.bootcampasj.proyectospringprueba.model.repository;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Estado;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EstadoJPARepository extends PagingAndSortingRepository<Estado, Integer> {

}
