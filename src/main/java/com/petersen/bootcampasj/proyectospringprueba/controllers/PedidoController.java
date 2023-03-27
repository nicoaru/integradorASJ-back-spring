package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoCreateDTO.PedidoCreateDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.PedidoResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.PedidoDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.PedidoMapper;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Pedido;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.PedidoServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
@Api(value = "controller pedidos", tags = "Pedidos")
public class PedidoController {
    private final PedidoServiceInterface servicePedido;
    private final PedidoMapper mapperPedido;

    /** Constructor con DI **/
    PedidoController(
            PedidoServiceInterface servicePedido,
            PedidoMapper mapperPedido) {
        this.servicePedido = servicePedido;
        this.mapperPedido = mapperPedido;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    @ApiOperation(value = "Traer todos los Pedidos")
    public ResponseEntity getAll(){
        try{
            List<Pedido> pedidos = servicePedido.getAll();
            List<PedidoResponseDTO> pedidosDTO = mapperPedido.listEntityToListDtoResponse(pedidos);
            return new ResponseEntity(pedidosDTO, HttpStatus.OK);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar por id")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en GET /pedidos/{id} - getById");
        try {
            Pedido pedido = servicePedido.getById(id);
            PedidoResponseDTO pedidoDTO = mapperPedido.entityToDtoResponse(pedido);
            return new ResponseEntity(pedidoDTO, HttpStatus.OK);

        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    @ApiOperation(value = "Crear nuevo Pedido")
    public ResponseEntity create(@RequestBody PedidoCreateDTO newPedido){
        System.out.println("Entró en POST /pedidos/{id} - updateById");
        try{
            Pedido newPedidoEntidad = mapperPedido.dtoCreateToEntity(newPedido);

            Pedido pedido = servicePedido.create(newPedidoEntidad);
            PedidoResponseDTO pedidoDTO = mapperPedido.entityToDtoResponse(pedido);

            return new ResponseEntity(pedidoDTO, HttpStatus.CREATED);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar datos de un Pedido por id")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody PedidoDTOupdt updatedPedido){
        System.out.println("Entró en PUT /pedidos/{id} - updateById");
        try{
            Pedido updatedPedidoEntidad = mapperPedido.dtoUpdtToEntity(updatedPedido);

            Pedido pedido = servicePedido.updateById(id, updatedPedidoEntidad);
            PedidoResponseDTO pedidoDTO = mapperPedido.entityToDtoResponse(pedido);

            return new ResponseEntity(pedidoDTO, HttpStatus.OK);

        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un Pedido por id")
    public ResponseEntity deleteById(@PathVariable Integer id){
        try{
            Pedido pedidoEliminado = servicePedido.deleteById(id);
            PedidoResponseDTO pedidoEliminadoDTO = mapperPedido.entityToDtoResponse(pedidoEliminado);
            return new ResponseEntity(pedidoEliminadoDTO, HttpStatus.OK);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
