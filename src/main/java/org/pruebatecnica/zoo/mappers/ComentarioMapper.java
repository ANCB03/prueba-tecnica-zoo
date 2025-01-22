package org.pruebatecnica.zoo.mappers;

import org.mapstruct.*;
import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.entities.Comentario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    @Mapping(source = "idAutor", target = "autor.idUsuario")
    @Mapping(source = "idComentarioPadre", target = "comentarioPadre.idComentario")
    @Mapping(source = "idAnimal", target = "animal.idAnimal")
    Comentario toEntity(ComentarioDto comentarioDto);

    @Mapping(source = "autor.idUsuario", target = "idAutor")
    @Mapping(source = "comentarioPadre.idComentario", target = "idComentarioPadre")
    @Mapping(source = "animal.idAnimal", target = "idAnimal")
    ComentarioDto toDto(Comentario comentario);

    List<ComentarioDto> toComentariolist(List<Comentario> entityList);

}
