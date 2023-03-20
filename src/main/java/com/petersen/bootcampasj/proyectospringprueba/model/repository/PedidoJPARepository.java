package com.petersen.bootcampasj.proyectospringprueba.model.repository;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Pedido;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PedidoJPARepository extends PagingAndSortingRepository<Pedido, Integer> {

}
