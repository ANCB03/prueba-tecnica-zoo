package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.EspecieResponse;
import org.pruebatecnica.zoo.entities.Especie;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecieResponseMapper {
    Especie toEntity(EspecieResponse especieResponse);

    EspecieResponse toDto(Especie especie);

    List<EspecieResponse> toEspecielist(List<Especie> entityList);
}
