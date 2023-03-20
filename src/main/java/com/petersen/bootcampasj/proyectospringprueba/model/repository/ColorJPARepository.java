package com.petersen.bootcampasj.proyectospringprueba.model.repository;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Color;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColorJPARepository extends PagingAndSortingRepository<Color, Integer> {

}
