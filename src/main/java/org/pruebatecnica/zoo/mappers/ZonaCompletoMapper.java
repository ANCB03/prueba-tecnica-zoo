package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.ZonaCompletoDto;
import org.pruebatecnica.zoo.entities.Zona;

@Mapper(componentModel = "spring")
public interface ZonaCompletoMapper {
    Zona toEntity(ZonaCompletoDto zonaCompletoDto);

    ZonaCompletoDto toDto(Zona zona);
}
