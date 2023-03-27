package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.MuebleMapper;
import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.exceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.*;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.*;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasMueble;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.MuebleServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
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
    @Override
    public List<Mueble> getAll() throws Exception {
        List<Mueble> muebles = (List<Mueble>) repository.findAll();
        return muebles;
    }

    @Override
    public Mueble getById(Integer id) throws Exception {
        Mueble mueble = repository.findById(id)
                                .orElse(null);
        // Si el mueble no existe tiro error
        if(mueble == null) {
            throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
        }
        return mueble;
    }

    @Override
    public Mueble create(Mueble newMueble) throws Exception {
        try {
            chequearDatos(newMueble);
            // Busco en BD las entidades hijas por ID
            EntidadesHijasMueble entidadesHijas = traerEntidadesHijas(newMueble);

            // Armo la entidad que voy a persistir y la mando a la DB
            newMueble.setPedido(entidadesHijas.getPedido());
            newMueble.setColor(entidadesHijas.getColor());
            newMueble.setModelo(entidadesHijas.getModelo());
            newMueble.setEstado(entidadesHijas.getEstado());

            Mueble mueble = repository.save(newMueble);
            return mueble;
        }
        catch (ValidationException err) {
            throw new HttpClientErrorExceptionWithData("Error de validación de datos", HttpStatus.BAD_REQUEST, "Bad request", err.getData());
        }

    }

    @Override
    public Mueble updateById(Integer id, Mueble updatedMueble) throws Exception {
        try {
            // Busco el pedido a actualizar
            Mueble muebleToUpdate = repository.findById(id)
                    .orElse(null);
            // Si el pedido a actualizar no existe tiro error
            if(muebleToUpdate == null) {
                throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
            }

            chequearDatos(updatedMueble);
            // Busco en BD las entidades hijas por ID
            EntidadesHijasMueble entidadesHijas = traerEntidadesHijas(updatedMueble);
            updatedMueble.setId(id);
            updatedMueble.setPedido(entidadesHijas.getPedido());
            updatedMueble.setColor(entidadesHijas.getColor());
            updatedMueble.setModelo(entidadesHijas.getModelo());
            updatedMueble.setEstado(entidadesHijas.getEstado());

            Mueble mueble = repository.save(updatedMueble);
            return mueble;
        }
        catch (ValidationException err) {
            throw new HttpClientErrorExceptionWithData("Error de validación de datos", HttpStatus.BAD_REQUEST, "Bad request", err.getData());
        }

    }

    @Override
    public Mueble deleteById(Integer id) throws Exception {

        Mueble mueble = repository.findById(id)
                                .orElse(null);
        // Si el mueble a eliminar no existe tiro error
        if(mueble == null) {
            throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
        }
        repository.deleteById(id);
        return mueble;

    }

    @Override
    public boolean existsById(Integer id) throws Exception {
        boolean existe = repository.existsById(id);
        return existe;
    }


    //** AUXILIARES **//
    //** AUXILIARES **//

    public HashMap<String, Object> chequearDatos(Mueble mueble) throws ValidationException {
        HashMap<String, Object> validaciones = new HashMap<>();

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


        if(validaciones.size() > 0) {
            ValidationException exception = new ValidationException("Error de validación de datos", validaciones);
            throw exception;
        }
        return validaciones;
    }

    public EntidadesHijasMueble traerEntidadesHijas(Mueble mueble) {
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


