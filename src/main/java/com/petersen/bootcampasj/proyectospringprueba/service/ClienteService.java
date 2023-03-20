package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.ClienteMapper;
import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.*;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.ClienteJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.MuebleJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.PedidoJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.TipoClienteJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasCliente;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.ClienteServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class ClienteService implements ClienteServiceInterface {
    private final ClienteJPARepository repository;
    private final PedidoJPARepository repositoryPedido;
    private final MuebleJPARepository repositoryMueble;
    private final TipoClienteJPARepository repositoryTipoCliente;

    private final ClienteMapper mapperCliente;

    /** Constructor con DI **/
    public ClienteService(ClienteJPARepository repository, PedidoJPARepository repositoryPedido, MuebleJPARepository repositoryMueble, TipoClienteJPARepository repositoryTipoCliente, ClienteMapper mapperCliente) {
        this.repository = repository;
        this.repositoryPedido = repositoryPedido;
        this.repositoryMueble = repositoryMueble;
        this.repositoryTipoCliente = repositoryTipoCliente;
        this.mapperCliente = mapperCliente;
    }


    /** Métodos **/
    /** Métodos **/
    @Override
    public List<Cliente> getAll() throws Exception {
        List<Cliente> clientes = (List<Cliente>) repository.findAll();
        return clientes;
    }

    @Override
    public Cliente getById(Integer id) throws Exception {
        Cliente cliente = repository.findById(id)
                .orElse(null);
        // Si el pedido a actualizar no existe tiro error
        if(cliente == null) {
            throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
        }
        return cliente;
    }

    @Override
    public Cliente create(Cliente newCliente) throws Exception {
        try {
            chequearDatos(newCliente);

            // Busco en BD las entidades hijas por ID
            EntidadesHijasCliente entidadesHijas = traerEntidadesHijas(newCliente);

            // Armo la entidad que voy a persistir y la mando a la DB
            newCliente.setTipoCliente(entidadesHijas.getTipoCliente());

            Cliente cliente = repository.save(newCliente);
            return cliente;
        }
        catch (ValidationException err) {
            throw new HttpClientErrorExceptionWithData("Error de validación de datos", HttpStatus.BAD_REQUEST, "Bad request", err.getData());
        }

    }

    @Override
    public Cliente updateById(Integer id, Cliente updatedCliente) throws Exception {
        try {
            // Busco el cliente a actualizar
            Cliente clienteToUpdate = repository.findById(id)
                    .orElse(null);
            // Si el pedido a actualizar no existe tiro error
            if(clienteToUpdate == null) {
                throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
            }

            chequearDatos(updatedCliente);

            // Busco en BD las entidades hijas por ID
            EntidadesHijasCliente entidadesHijas = traerEntidadesHijas(updatedCliente);
            updatedCliente.setId(id);
            updatedCliente.setTipoCliente(entidadesHijas.getTipoCliente());
            updatedCliente.setPedidos(clienteToUpdate.getPedidos());

            Cliente cliente = repository.save(updatedCliente);
            return cliente;
        }
        catch (ValidationException err) {
            throw new HttpClientErrorExceptionWithData("Error de validación de datos", HttpStatus.BAD_REQUEST, "Bad request", err.getData());
        }
    }

    @Override
    public Cliente deleteById(Integer id) throws Exception {
        Cliente cliente = repository.findById(id)
                .orElse(null);

        // Si el pedido a eliminar no existe tiro error
        if(cliente == null) {
            throw new HttpClientErrorExceptionWithData("Not found", HttpStatus.NOT_FOUND, "Not found", null);
        }

        repository.deleteById(id);
        return cliente;

    }

    @Override
    public boolean existsById(Integer id) throws Exception {
        boolean existe = repository.existsById(id);
        return existe;
    }


    //** AUXILIARES **//
    //** AUXILIARES **//

    public HashMap<String, Object> chequearDatos(Cliente cliente) throws ValidationException {
        HashMap<String, Object> validaciones = new HashMap<>();

        if(cliente.getTipoCliente() != null && cliente.getTipoCliente().getId() != null) {
            boolean _tipoClienteExiste = repositoryTipoCliente.existsById(cliente.getTipoCliente().getId());
            if(!_tipoClienteExiste) validaciones.put("tipoCliente", "no existe en la DB con ese id");
        }


        if(validaciones.size() > 0) {
            ValidationException exception = new ValidationException("Error de validación de datos", validaciones);
            throw exception;
        }
        return validaciones;
    }

    public EntidadesHijasCliente traerEntidadesHijas(Cliente cliente) throws Exception {
        EntidadesHijasCliente valores = new EntidadesHijasCliente();
        if(cliente.getTipoCliente() == null || cliente.getTipoCliente().getId() == null) valores.setTipoCliente(null);
        else {
            TipoCliente _tipoCliente = repositoryTipoCliente.findById(cliente.getTipoCliente().getId())
                    .orElse(null);
            valores.setTipoCliente(_tipoCliente);
        }

        return valores;
    }


}


/*
    public ResponseEntity getAll() {
        try {
            List<Cliente> clientes = (List<Cliente>) repository.findAll();
            return new ResponseEntity(clientes, HttpStatus.OK);
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
            Cliente cliente = repository.findById(id)
                                .orElse(null);
            if(cliente != null){
                return new ResponseEntity(cliente, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Cliente no encontrado", null);
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
    public ResponseEntity create(ClienteDTOcreate newCliente) {
        try {
            Cliente newClienteEntity = mapperCliente.dtoCreateToEntity(newCliente);

            // Chequeo si el tipo de cliente elegido existe
            if(newClienteEntity.getTipoCliente() != null && newClienteEntity.getTipoCliente().getId() != null) {
                boolean tipoClienteExiste = repositoryTipoCliente.existsById(newClienteEntity.getTipoCliente().getId());
                if(!tipoClienteExiste) {
                    HttpErrorResponseBody errorBody = new HttpErrorResponseBody("No existe tipo de cliente con ese id", null);
                    return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
                }
            }

            Cliente cliente = repository.save(newClienteEntity);
            return new ResponseEntity(cliente, HttpStatus.CREATED);
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateById(Integer id, ClienteDTOupdt updatedClienteReq) {
        try {
            Cliente _clienteToUpdate = repository.findById(id)
                                .orElse(null);

            TipoCliente _tipoCliente = null;

            Cliente updatedClienteEntidad = mapperCliente.dtoUpdtToEntity(updatedClienteReq);
            System.out.println("updatedClienteEntidad:\n"+updatedClienteEntidad);

            if(updatedClienteReq.getTipoCliente() != null && updatedClienteReq.getTipoCliente().getId() != null) {
                _tipoCliente = repositoryTipoCliente.findById(updatedClienteReq.getTipoCliente().getId())
                        .orElse(null);
            }
            System.out.println("updatedClienteEntidad:\n"+updatedClienteEntidad);


            if(_clienteToUpdate != null) {
                updatedClienteEntidad.setId(id);
                updatedClienteEntidad.setTipoCliente(_tipoCliente);
                updatedClienteEntidad.setPedidos(_clienteToUpdate.getPedidos());


                Cliente result = repository.save(updatedClienteEntidad);
                return new ResponseEntity(result, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Cliente con id %s", id), null);
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

    @Override
    public ResponseEntity deleteById(Integer id) {
        try {
            Cliente cliente = repository.findById(id)
                                    .orElse(null);

            if(cliente != null) {
                repository.deleteById(id);
                return new ResponseEntity(cliente, HttpStatus.OK);
            }
            else {
                HttpErrorResponseBody errorBody = new HttpErrorResponseBody(String.format("No existe Cliente con id %s", id), null);
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