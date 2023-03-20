package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.DTO.MuebleDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.MuebleDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.MuebleMapper;
import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.*;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.*;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasMueble;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.MuebleServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class MuebleService implements MuebleServiceInterface {
    private final MuebleJPARepository repository;
    private final PedidoJPARepository repositoryPedido;
    private final ColorJPARepository repositoryColor;
    private final ModeloJPARepository repositoryModelo;
    private final EstadoJPARepository repositoryEstado;
    private final MuebleMapper mapperMueble;


    /** Constructor con DI **/
    public MuebleService(
            MuebleJPARepository repository,
            MuebleMapper mapperMueble,
            PedidoJPARepository repositoryPedido,
            ColorJPARepository repositoryColor,
            ModeloJPARepository repositoryModelo,
            EstadoJPARepository repositoryEstado) {
        this.repository = repository;
        this.mapperMueble = mapperMueble;
        this.repositoryPedido = repositoryPedido;
        this.repositoryColor = repositoryColor;
        this.repositoryModelo = repositoryModelo;
        this.repositoryEstado = repositoryEstado;
    }


    /** Métodos **/
    /** Métodos **/

    public ResponseEntity getAll() {
        try {
            List<Mueble> muebles = (List<Mueble>) repository.findAll();
            return new ResponseEntity(muebles, HttpStatus.OK);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getById(Integer id) {
        try {
            Mueble mueble = repository.findById(id)
                                .orElse(null);
            if(mueble != null){
                return new ResponseEntity(mueble, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Mueble no encontrado", null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity create(MuebleDTOcreate newMueble) throws Exception {
            Mueble newMuebleEntity = mapperMueble.dtoCreateToEntity(newMueble);

        // Chequeo los datos ingresados:
        // 'Pedido' y 'Modelo' no pueden ser nulos y tiene que tener un ID existente
        // 'Color' y 'Estado' pueden ser nulos y tienen que tener un ID existente
            // HashMap<String, String> validaciones = chequearDatos(newMuebleEntity);
            //Si hay algún dato mal respondo con BAD_REQUEST
            /*System.out.println("validaciones: "+validaciones);
            if(validaciones.size() > 0) {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Datos inválidos", validaciones);
                return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
            }*/
        // Busco en BD las entidades hijas por ID
            EntidadesHijasMueble entidadesHijas = traerEntidadesHijas(newMuebleEntity);

            // Armo la entidad que voy a persistir y la mando a la DB
            newMuebleEntity.setPedido(entidadesHijas.getPedido());
            newMuebleEntity.setColor(entidadesHijas.getColor());
            newMuebleEntity.setModelo(entidadesHijas.getModelo());
            newMuebleEntity.setEstado(entidadesHijas.getEstado());

            Mueble mueble = repository.save(newMuebleEntity);
            return new ResponseEntity(mueble, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateById(Integer id, MuebleDTOupdt updatedMuebleReq) throws Exception {
            Mueble updatedMuebleEntidad = mapperMueble.dtoUpdtToEntity(updatedMuebleReq);
            Mueble _muebleToUpdate = repository.findById(id)
                    .orElse(null);
    // Si el pedido a actualizar no existe respondo con NOT_FOUND
            if(_muebleToUpdate == null) {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Mueble con id %s", id), null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
    // Chequeo los datos ingresados:
        // 'Pedido' y 'Modelo' no pueden ser nulos y tiene que tener un ID existente
        // 'Color' y 'Estado' pueden ser nulos y tienen que tener un ID existente
            //HashMap<String, String> validaciones = chequearDatos(updatedMuebleEntidad);
    // Si hay algún dato mal respondo con BAD_REQUEST
            /*System.out.println("validaciones: "+validaciones);
            if(validaciones.size() > 0) {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Datos inválidos", validaciones);
                return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
            }*/
    // Busco en BD las entidades hijas por ID
            EntidadesHijasMueble entidadesHijas = traerEntidadesHijas(updatedMuebleEntidad);
            updatedMuebleEntidad.setId(id);
            updatedMuebleEntidad.setPedido(entidadesHijas.getPedido());
            updatedMuebleEntidad.setColor(entidadesHijas.getColor());
            updatedMuebleEntidad.setModelo(entidadesHijas.getModelo());
            updatedMuebleEntidad.setEstado(entidadesHijas.getEstado());

            Mueble result = repository.save(updatedMuebleEntidad);
            return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteById(Integer id) {
        try {
            Mueble mueble = repository.findById(id)
                                    .orElse(null);

            if(mueble != null) {
                repository.deleteById(id);
                return new ResponseEntity(mueble, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Mueble con id %s", id), null);
                return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(true, err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //** AUXILIARES **//
    //** AUXILIARES **//

    public HashMap<String, String> chequearDatos(Mueble mueble) throws Exception {
        HashMap<String, String> validaciones = new HashMap<>();

        if(mueble.getPedido() != null && mueble.getPedido().getId() != null) {
            boolean _pedidoExiste = repositoryPedido.existsById(mueble.getPedido().getId());
            if(!_pedidoExiste) validaciones.put("pedido", "no existe en la DB con ese id");
        } else validaciones.put("pedido", "no debe ser nulo");

        if(mueble.getModelo() != null && mueble.getModelo().getId() != null) {
            boolean _modeloExiste = repositoryModelo.existsById(mueble.getModelo().getId());
            if(!_modeloExiste) validaciones.put("modelo", "no existe en la DB con ese id");
        } else validaciones.put("modelo", "no debe ser nulo");

        if(mueble.getColor() != null && mueble.getColor().getId() != null) {
            boolean _colorExiste = repositoryColor.existsById(mueble.getColor().getId());
            if(!_colorExiste) validaciones.put("color", "no existe en la DB con ese id");
        }

        if(mueble.getEstado() != null && mueble.getEstado().getId() != null) {
            boolean _estadoExiste = repositoryEstado.existsById(mueble.getEstado().getId());
            if(!_estadoExiste) validaciones.put("estado", "no existe en la DB con ese id");
        }

        return validaciones;
    }

    public EntidadesHijasMueble traerEntidadesHijas(Mueble mueble) throws Exception {
        EntidadesHijasMueble valores = new EntidadesHijasMueble();
        if(mueble.getPedido() == null || mueble.getPedido().getId() == null) valores.setPedido(null);
        else {
            Pedido _pedido = repositoryPedido.findById(mueble.getPedido().getId())
                    .orElse(null);
            valores.setPedido(_pedido);
        }
        if(mueble.getModelo() == null || mueble.getModelo().getId() == null) valores.setModelo(null);
        else {
            Modelo _modelo = repositoryModelo.findById(mueble.getModelo().getId())
                    .orElse(null);
            valores.setModelo(_modelo);
        }
        if(mueble.getColor() == null || mueble.getColor().getId() == null) valores.setColor(null);
        else {
            Color _color = repositoryColor.findById(mueble.getColor().getId())
                    .orElse(null);
            valores.setColor(_color);
        }
        if(mueble.getEstado() == null || mueble.getEstado().getId() == null) valores.setEstado(null);
        else {
            Estado _estado = repositoryEstado.findById(mueble.getEstado().getId())
                    .orElse(null);
            valores.setEstado(_estado);
        }

        return valores;
    }

}


