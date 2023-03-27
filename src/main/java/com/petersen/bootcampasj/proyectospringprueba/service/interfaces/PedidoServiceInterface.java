package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Mueble;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Pedido;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasPedido;

import java.util.HashMap;
import java.util.List;

public interface PedidoServiceInterface {

    List<Pedido> getAll() throws Exception;
    Pedido getById(Integer id) throws Exception;
    Pedido create(Pedido newEntity) throws Exception;
    //ResponseEntity createMany(List<T> newEntitiesList);
    Pedido updateById(Integer id, Pedido updatedEntity) throws Exception;
    Pedido deleteById(Integer id) throws Exception;

    /**/
    boolean existsById(Integer id) throws Exception;
    HashMap<String, Object> chequearDatos(Pedido pedido) throws ValidationException;
    EntidadesHijasPedido traerEntidadesHijas(Pedido pedido) throws Exception;
    HashMap<String, Object> chequearDatosDeMueblesACrear(List<Mueble> muebles) throws ValidationException;
}
