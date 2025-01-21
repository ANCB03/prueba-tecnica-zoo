package org.pruebatecnica.zoo.mappers;

import org.mapstruct.*;
import org.pruebatecnica.zoo.dtos.EspecieDto;
import org.pruebatecnica.zoo.entities.Especie;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecieMapper {
    @Mapping(source = "idZona", target = "zona.idZona")
    Especie toEntity(EspecieDto especieDto);

    @Mapping(source = "zona.idZona", target = "idZona")
    EspecieDto toDto(Especie especie);

    List<EspecieDto> toEspecielist(List<Especie> entityList);
}
