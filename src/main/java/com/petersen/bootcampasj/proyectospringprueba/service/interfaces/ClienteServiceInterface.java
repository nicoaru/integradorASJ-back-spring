package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasCliente;

import java.util.HashMap;
import java.util.List;

public interface ClienteServiceInterface {

    List<Cliente> getAll() throws Exception;
    Cliente getById(Integer id) throws Exception;
    Cliente create(Cliente newCliente) throws Exception;
    //ResponseEntity createMany(List<T> newEntitiesList);
    Cliente updateById(Integer id, Cliente updatedCliente) throws Exception;
    Cliente deleteById(Integer id) throws Exception;
    //ResponseEntity getMueblesIdCliente(Integer id);
    boolean existsById(Integer id) throws Exception;


    /**/
    HashMap<String, Object> chequearDatos(Cliente cliente) throws ValidationException;
    EntidadesHijasCliente traerEntidadesHijas(Cliente cliente) throws Exception;

}
