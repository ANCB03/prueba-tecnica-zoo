package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.EspecieSinZonaDto;
import org.pruebatecnica.zoo.entities.Especie;

@Mapper(componentModel = "spring")
public interface EspecieSinZonaResponseMapper {
    Especie toEntity(EspecieSinZonaDto especieSinZonaDto);

    EspecieSinZonaDto toDto(Especie especie);
}
