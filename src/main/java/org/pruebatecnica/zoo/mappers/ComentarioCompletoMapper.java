package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.ComentarioCompletoDto;
import org.pruebatecnica.zoo.entities.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioCompletoMapper {
    @Mapping(source = "idComentarioPadre", target = "comentarioPadre.idComentario")
    Comentario toEntity(ComentarioCompletoDto comentarioCompletoDto);

    @Mapping(source = "comentarioPadre.idComentario", target = "idComentarioPadre")
    ComentarioCompletoDto toDto(Comentario comentario);
}
