package com.petersen.bootcampasj.proyectospringprueba.service.interfaces;

import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Mueble;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasMueble;

import java.util.HashMap;
import java.util.List;

public interface MuebleServiceInterface {

    List<Mueble> getAll() throws Exception;
    Mueble getById(Integer id) throws Exception;
    Mueble create(Mueble newMueble) throws Exception;
    //ResponseEntity createMany(List<T> newEntitiesList);
    Mueble updateById(Integer id, Mueble updatedMueble) throws Exception;
    Mueble deleteById(Integer id) throws Exception;

    /**/
    boolean existsById(Integer id) throws Exception;
    HashMap<String, Object> chequearDatos(Mueble mueble) throws ValidationException;
    EntidadesHijasMueble traerEntidadesHijas(Mueble mueble);

}
