package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.ComentarioResponseSinCom;
import org.pruebatecnica.zoo.entities.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioResponseSinComMapper {
    Comentario toEntity(ComentarioResponseSinCom comentarioResponseSinCom);

    ComentarioResponseSinCom toDto(Comentario comentario);
}
