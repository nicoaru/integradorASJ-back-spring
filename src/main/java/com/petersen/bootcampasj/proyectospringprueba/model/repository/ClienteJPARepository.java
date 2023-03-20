package com.petersen.bootcampasj.proyectospringprueba.model.repository;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.TipoCliente;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteJPARepository extends PagingAndSortingRepository<Cliente, Integer> {

}
