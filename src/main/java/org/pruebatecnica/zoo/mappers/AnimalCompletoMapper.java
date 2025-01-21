package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.AnimalCompletoDto;
import org.pruebatecnica.zoo.entities.Animal;

@Mapper(componentModel = "spring")
public interface AnimalCompletoMapper {
    @Mapping(source = "idEspecie", target = "especie.idEspecie")
    Animal toEntity(AnimalCompletoDto animalCompletoDto);

    @Mapping(source = "especie.idEspecie", target = "idEspecie")
    AnimalCompletoDto toDto(Animal animal);
}
