package org.pruebatecnica.zoo.mappers;

import org.mapstruct.*;
import org.pruebatecnica.zoo.dtos.RolDto;
import org.pruebatecnica.zoo.entities.Rol;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {
    Rol toEntity(RolDto rolDto);

    RolDto toDto(Rol rol);

    List<RolDto> toRollist(List<Rol> entityList);

    @Mapping(target = "idRol", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RolDto rolDto, @MappingTarget Rol rol);
}
