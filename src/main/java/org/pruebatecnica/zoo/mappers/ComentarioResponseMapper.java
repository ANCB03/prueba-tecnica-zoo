package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.ComentarioResponse;
import org.pruebatecnica.zoo.entities.Comentario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioResponseMapper {
    Comentario toEntity(ComentarioResponse comentarioResponse);

    ComentarioResponse toDto(Comentario comentario);

    List<ComentarioResponse> toComentariolist(List<Comentario> entityList);
}
