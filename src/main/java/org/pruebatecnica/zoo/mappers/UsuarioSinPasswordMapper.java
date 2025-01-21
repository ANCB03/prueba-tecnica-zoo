package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.UsuarioSinPasswordDto;
import org.pruebatecnica.zoo.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioSinPasswordMapper {

    @Mapping(source = "idRol", target = "rol.idRol")
    Usuario toEntity(UsuarioSinPasswordDto usuarioSinPasswordDto);

    @Mapping(source = "rol.idRol", target = "idRol")
    UsuarioSinPasswordDto toDto(Usuario usuario);
}
