package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.EspecieCompletoDto;
import org.pruebatecnica.zoo.entities.Especie;

@Mapper(componentModel = "spring")
public interface EspecieCompletoMapper {
    @Mapping(source = "idZona", target = "zona.idZona")
    Especie toEntity(EspecieCompletoDto especieCompletoDto);

    @Mapping(source = "zona.idZona", target = "idZona")
    EspecieCompletoDto toDto(Especie especie);
}
