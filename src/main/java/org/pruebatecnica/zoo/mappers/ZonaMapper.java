package org.pruebatecnica.zoo.mappers;

import org.mapstruct.*;
import org.pruebatecnica.zoo.dtos.ZonaDto;
import org.pruebatecnica.zoo.entities.Zona;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZonaMapper {
    Zona toEntity(ZonaDto zonaDto);

    ZonaDto toDto(Zona zona);

    List<ZonaDto> toZonalist(List<Zona> entityList);

    @Mapping(target = "nombreZona", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ZonaDto zonaDto, @MappingTarget Zona zona);
}
