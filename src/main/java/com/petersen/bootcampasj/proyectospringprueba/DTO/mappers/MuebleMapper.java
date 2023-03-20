package com.petersen.bootcampasj.proyectospringprueba.DTO.mappers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.MuebleDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.MuebleDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.MuebleResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.auxDto.Cliente_Pedido_MuebleResponse;
import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.auxDto.Pedido_MuebleResponse;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuebleMapper {

    //** MuebleDTOupdt **//
    @Mapping(source = "id", target = "id")
    @Mapping(source = "largo", target = "largo")
    @Mapping(source = "alto", target = "alto")
    @Mapping(source = "profundidad", target = "profundidad")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "pedido", target = "pedido")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "estado", target = "estado")
    Mueble dtoUpdtToEntity(MuebleDTOupdt dto);


    //** MuebleDTOcreate **//
    @Mapping(source = "largo", target = "largo")
    @Mapping(source = "alto", target = "alto")
    @Mapping(source = "profundidad", target = "profundidad")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "pedido", target = "pedido")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "estado", target = "estado")
    Mueble dtoCreateToEntity(MuebleDTOcreate dto);


    //** MuebleDTOresponse **//
    MuebleResponseDTO entityToMueblesResponseDto(Mueble mueble);
    Pedido_MuebleResponse entityToPedido_MuebResp(Pedido pedido);
    Cliente_Pedido_MuebleResponse entityToCliente_Pedido_MuebResp(Cliente cliente);
    List<MuebleResponseDTO> listEntityToListMueblesResponseDto(List<Mueble> mueble);

}
