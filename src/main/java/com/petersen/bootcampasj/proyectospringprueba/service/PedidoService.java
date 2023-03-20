package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.PedidoMapper;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.*;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.ClienteJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.PedidoJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasPedido;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.MuebleServiceInterface;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.PedidoServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class PedidoService implements PedidoServiceInterface {
    private final PedidoJPARepository repository;
    private final ClienteJPARepository repositoryCliente;
    private final MuebleServiceInterface serviceMueble;
    private final PedidoMapper mapperPedido;

    /** Constructor con DI **/
    public PedidoService(
            PedidoJPARepository repository,
            ClienteJPARepository repositoryCliente,
            MuebleServiceInterface serviceMueble,
            PedidoMapper mapperPedido) {
        this.repository = repository;
        this.repositoryCliente = repositoryCliente;
        this.mapperPedido = mapperPedido;
        this.serviceMueble = serviceMueble;
    }


    /** Métodos **/
    /** Métodos **/
    @Override
    public List<Pedido> getAll() throws Exception {
        List<Pedido> pedidos = (List<Pedido>) repository.findAll();
        return pedidos;
    }

    @Override
    public Pedido getById(Integer id) throws Exception {
        Pedido pedido = repository.findById(id)
                .orElse(null);
        // Si el pedido no existe tiro error
        if(pedido == null) {
            throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
        }
        return pedido;
    }

    @Override
    public Pedido create(Pedido newPedido) throws Exception {
        try {
            chequearDatos(newPedido);
            chequearDatosDeMueblesACrear(newPedido.getMuebles());
            // Busco en BD las entidades hijas por ID
            EntidadesHijasPedido entidadesHijas = traerEntidadesHijas(newPedido);

            // Armo la entidad que voy a persistir y la mando a la DB
            newPedido.setCliente(entidadesHijas.getCliente());


            Pedido pedido = repository.save(newPedido);
            return pedido;
        }
        catch (ValidationException err) {
            throw new HttpClientErrorExceptionWithData("Error de validación de datos", HttpStatus.BAD_REQUEST, "Bad request", err.getData());
        }

    }

    @Override
    public Pedido updateById(Integer id, Pedido updatedPedido) throws Exception {
        try {
            // Busco el pedido a actualizar
            Pedido pedidoToUpdate = repository.findById(id)
                    .orElse(null);
            // Si el pedido a actualizar no existe tiro error
            if(pedidoToUpdate == null) {
                throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
            }

            chequearDatos(updatedPedido);

            // Busco en BD las entidades hijas por ID
            EntidadesHijasPedido entidadesHijas = traerEntidadesHijas(updatedPedido);
            updatedPedido.setId(id);
            updatedPedido.setCliente(entidadesHijas.getCliente());
            updatedPedido.setMuebles(pedidoToUpdate.getMuebles());

            Pedido pedido = repository.save(updatedPedido);
            return pedido;
        }
        catch (ValidationException err) {
            throw new HttpClientErrorExceptionWithData("Error de validación de datos", HttpStatus.BAD_REQUEST, "Bad request", err.getData());
        }

    }

    @Override
    public Pedido deleteById(Integer id) throws Exception {

        Pedido pedido = repository.findById(id)
                .orElse(null);
        // Si el pedido a eliminar no existe tiro error
        if(pedido == null) {
            throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
        }
        repository.deleteById(id);
        return pedido;

    }

    @Override
    public boolean existsById(Integer id) throws Exception {
        boolean existe = repository.existsById(id);
        return existe;
    }


    //** AUXILIARES **//
    //** AUXILIARES **//

    public HashMap<String, Object> chequearDatos(Pedido pedido) throws ValidationException {
        HashMap<String, Object> validaciones = new HashMap<>();

        if(pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            boolean _clienteExiste = repositoryCliente.existsById(pedido.getCliente().getId());
            if(!_clienteExiste) validaciones.put("cliente", "no existe en la DB con ese id");
        } else validaciones.put("cliente", "no debe ser nulo");

        if(validaciones.size() > 0) {
            ValidationException exception = new ValidationException("Error de validación de datos", validaciones);
            throw exception;
        }
        return validaciones;
    }

    public EntidadesHijasPedido traerEntidadesHijas(Pedido pedido) throws Exception {
        EntidadesHijasPedido valores = new EntidadesHijasPedido();
        if(pedido.getCliente() == null || pedido.getCliente().getId() == null) valores.setCliente(null);
        else {
            Cliente _cliente = repositoryCliente.findById(pedido.getCliente().getId())
                    .orElse(null);
            valores.setCliente(_cliente);
        }

        return valores;
    }

    public HashMap<String, Object> chequearDatosDeMueblesACrear(List<Mueble> muebles) throws ValidationException {
        HashMap<String, Object> validaciones = new HashMap<String, Object>();
        if(muebles == null) return validaciones;

        final int[] ix = {0};
        muebles.forEach((mueble) -> {
            HashMap<String, Object> validacionMueble;
            try {
                serviceMueble.chequearDatos(mueble);
                ix[0] += 1;
            } catch (ValidationException err) {
                validacionMueble = err.getData();
                validacionMueble.remove("pedido");
                if(validacionMueble.size() > 0 ) validaciones.put(String.format("mueble %s", (ix[0]+1)), validacionMueble);
                ix[0] += 1;
            }
        });

        if(validaciones.size() > 0) {
            ValidationException exception = new ValidationException("Error de validación de datos", validaciones);
            throw exception;
        }

        return validaciones;
    }


}

/*
    final int[] ix = {0};
    muebles.forEach((mueble) -> {
        try {
            HashMap<String, Object> validacionMueble = serviceMueble.chequearDatos(mueble);
            validacionMueble.remove("pedido");
            if(validacionMueble.size() > 0 ) validaciones.put(String.format("mueble %s", (ix[0]+1)), validacionMueble);
            ix[0] += 1;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    });

    @Override
    public ResponseEntity create(PedidoDTOcreate newPedido) {
        try {
            Pedido newPedidoEntity = mapperPedido.dtoCreateToEntity(newPedido);

            if(newPedidoEntity.getCliente() == null || newPedidoEntity.getCliente().getId() == null) {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Cliente no puede ser null", null);
                return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
            }
            else {
                boolean clienteExiste = repositoryCliente.existsById(newPedidoEntity.getCliente().getId());
                if(!clienteExiste) {
                    HttpErrorResponseBody errorBody = new HttpErrorResponseBody("No existe cliente con ese id", null);
                    return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
                }
            }

            Pedido pedido = repository.save(newPedidoEntity);
            return new ResponseEntity(pedido, HttpStatus.CREATED);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

/*
    @Override
    public ResponseEntity updateById(Integer id, PedidoDTOupdt updatedPedidoReq) {
        try {
            Pedido _pedidoToUpdate = repository.findById(id)
                    .orElse(null);

            Pedido updatedPedidoEntidad = mapperPedido.dtoUpdtToEntity(updatedPedidoReq);

            Cliente _cliente = null;
            // Chequeo el cliente
            if(updatedPedidoReq.getCliente() == null || updatedPedidoReq.getCliente().getId() == null) {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Cliente no puede ser nulo", null);
                return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
            }
            else {
                _cliente = repositoryCliente.findById(updatedPedidoEntidad.getCliente().getId())
                        .orElse(null);
                if(_cliente == null) {
                    HttpErrorResponseBody errorBody = new HttpErrorResponseBody("No existe cliente con ese id", null);
                    return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
                }
            }

            if(_pedidoToUpdate != null) {
                updatedPedidoEntidad.setId(id);
                updatedPedidoEntidad.setCliente(_cliente);
                updatedPedidoEntidad.setMuebles(_pedidoToUpdate.getMuebles());

                Pedido result = repository.save(updatedPedidoEntidad);
                return new ResponseEntity(result, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Pedido con id %s", id), null);
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
*/

/*
    @Override
    public ResponseEntity deleteById(Integer id) {
        try {
            Pedido pedido = repository.findById(id)
                    .orElse(null);

            if(pedido != null) {
                repository.deleteById(id);
                return new ResponseEntity(pedido, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Pedido con id %s", id), null);
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
*/