package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.ComentarioSinAutorDto;
import org.pruebatecnica.zoo.entities.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioSinAutorMapper {
    @Mapping(source = "idAnimal", target = "animal.idAnimal")
    @Mapping(source = "idComentario", target = "comentarioPadre.idComentario")
    Comentario toEntity(ComentarioSinAutorDto comentarioSinAutorDto);

    @Mapping(source = "animal.idAnimal", target = "idAnimal")
    @Mapping(source = "comentarioPadre.idComentario", target = "idComentario")
    ComentarioSinAutorDto toDto(Comentario comentario);

}
