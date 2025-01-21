package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.AnimalSinEspecieDto;
import org.pruebatecnica.zoo.entities.Animal;

@Mapper(componentModel = "spring")
public interface AnimalSinEspecieMapper {
    Animal toEntity(AnimalSinEspecieDto animalSinEspecieDto);

    AnimalSinEspecieDto toDto(Animal animal);
}
