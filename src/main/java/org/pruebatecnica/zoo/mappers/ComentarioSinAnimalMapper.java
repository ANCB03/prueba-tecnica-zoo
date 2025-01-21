package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.ComentarioSinAnimalDto;
import org.pruebatecnica.zoo.entities.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioSinAnimalMapper {
    @Mapping(source = "idAutor", target = "autor.idUsuario")
    @Mapping(source = "idComentario", target = "comentarioPadre.idComentario")
    Comentario toEntity(ComentarioSinAnimalDto comentarioSinAnimalDto);

    @Mapping(source = "autor.idUsuario", target = "idAutor")
    @Mapping(source = "comentarioPadre.idComentario", target = "idComentario")
    ComentarioSinAnimalDto toDto(Comentario comentario);
}
