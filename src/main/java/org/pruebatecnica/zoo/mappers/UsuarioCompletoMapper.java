package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.UsuarioCompletoDto;
import org.pruebatecnica.zoo.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioCompletoMapper {

    @Mapping(source = "idRol", target = "rol.idRol")
    Usuario toEntity(UsuarioCompletoDto usuarioCompletoDto);
    @Mapping(source = "rol.idRol", target = "idRol")
    UsuarioCompletoDto toDto(Usuario usuario);
}
