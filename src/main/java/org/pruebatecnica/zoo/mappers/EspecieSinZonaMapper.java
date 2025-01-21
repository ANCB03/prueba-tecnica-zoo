package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.EspecieDto;
import org.pruebatecnica.zoo.entities.Especie;

@Mapper(componentModel = "spring")
public interface EspecieSinZonaMapper {
    Especie toEntity(EspecieDto especieDto);

    EspecieDto toDto(Especie especie);
}
