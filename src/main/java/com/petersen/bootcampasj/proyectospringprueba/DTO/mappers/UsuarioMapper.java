package com.petersen.bootcampasj.proyectospringprueba.DTO.mappers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.usuarios.UsuarioResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    //** DTOupdt **//
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    UsuarioResponseDTO entityToDto(Usuario entity);



}
