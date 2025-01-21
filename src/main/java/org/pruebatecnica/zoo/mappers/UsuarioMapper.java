package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.UsuarioDto;
import org.pruebatecnica.zoo.dtos.UsuarioSinPasswordDto;
import org.pruebatecnica.zoo.entities.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "idRol", target = "rol.idRol")
    Usuario toEntity(UsuarioDto usuarioDto);
    @Mapping(source = "rol.idRol", target = "idRol")
    UsuarioDto toDto(Usuario usuario);

    @Mapping(source = "rol.idRol", target = "idRol")
    UsuarioSinPasswordDto toUsuarioSinPasswordDto(Usuario usuario);

    List<UsuarioSinPasswordDto> toUsuariolist(List<Usuario> entityList);
}
