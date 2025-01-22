package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.UsuarioResponse;
import org.pruebatecnica.zoo.entities.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioResponseMapper {
    Usuario toEntity(UsuarioResponse usuarioResponse);

    UsuarioResponse toDto(Usuario usuario);

    List<UsuarioResponse> toUsuariolist(List<Usuario> entityList);
}
