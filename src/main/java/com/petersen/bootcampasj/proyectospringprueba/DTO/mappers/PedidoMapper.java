package com.petersen.bootcampasj.proyectospringprueba.DTO.mappers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoCreateDTO.PedidoCreateDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.auxDto.Mueble_PedidoResponse;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.pedidoResponseDTO.PedidoResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.pedidos.PedidoDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Mueble;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    //** DTOupdt **//
    @Mapping(source = "id", target = "id")
    @Mapping(source = "direccionEntrega", target = "direccionEntrega")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "cliente", target = "cliente")
    Pedido dtoUpdtToEntity(PedidoDTOupdt dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "direccionEntrega", target = "direccionEntrega")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "cliente", target = "cliente")
    PedidoDTOupdt entityToDtoUpdt(Pedido entity);


    //** DTOcreate **//
    @Mapping(source = "direccionEntrega", target = "direccionEntrega")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "muebles", target = "muebles")
    Pedido dtoCreateToEntity(PedidoCreateDTO dto);

    @Mapping(source = "direccionEntrega", target = "direccionEntrega")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "muebles", target = "muebles")
    PedidoCreateDTO entityToDtoCreate(Pedido entity);


    //** DTOresponse **//

    PedidoResponseDTO entityToDtoResponse(Pedido entity);

    Mueble_PedidoResponse entityToMueb_PedidoRespDTO(Mueble mueble);

    List<PedidoResponseDTO> listEntityToListDtoResponse(List<Pedido> pedidos);

    /**/





}
