package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.entities.Rol;

@Mapper(componentModel = "spring")
public interface RolCompletoMapper {
    Rol toEntity(RolCompletoDto rolCompletoDto);

    RolCompletoDto toDto(Rol rol);
}
