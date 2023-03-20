package com.petersen.bootcampasj.proyectospringprueba.DTO.mappers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.clientes.ClienteDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "tipoCliente", target = "tipoCliente")
    ClienteDTOupdt entityToDtoUpdt(Cliente entity);


    //** DTOcreate **//

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "tipoCliente", target = "tipoCliente")
    Cliente dtoCreateToEntity(ClienteDTOcreate dto);


    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "notas", target = "notas")
    @Mapping(source = "tipoCliente", target = "tipoCliente")
    ClienteDTOcreate entityToDtoCreate(Cliente entity);

}
