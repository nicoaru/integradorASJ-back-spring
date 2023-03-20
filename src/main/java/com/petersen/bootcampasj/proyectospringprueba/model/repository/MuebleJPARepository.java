package com.petersen.bootcampasj.proyectospringprueba.model.repository;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Mueble;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MuebleJPARepository extends PagingAndSortingRepository<Mueble, Integer> {


}
