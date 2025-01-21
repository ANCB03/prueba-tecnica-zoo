package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.UsuarioSinRolDto;
import org.pruebatecnica.zoo.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioSinRolMapper {
    Usuario toEntity(UsuarioSinRolDto usuarionSinRolDto);

    UsuarioSinRolDto toDto(Usuario usuario);
}
