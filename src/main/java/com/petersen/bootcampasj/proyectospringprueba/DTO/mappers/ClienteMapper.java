package com.petersen.bootcampasj.proyectospringprueba.DTO.mappers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteSoloDTO;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    //** DTOupdt **//
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "tipoCliente", target = "tipoCliente")
    Cliente dtoUpdtToEntity(ClienteDTOupdt dto);

    //** DTOcreate **//
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "tipoCliente", target = "tipoCliente")
    Cliente dtoCreateToEntity(ClienteDTOcreate dto);

    //** DTOsoloCliente **//
    ClienteSoloDTO entityToClienteSoloDto(Cliente entity);
    List<ClienteSoloDTO> listEntityToClienteSoloDto(List<Cliente> entity);

}
