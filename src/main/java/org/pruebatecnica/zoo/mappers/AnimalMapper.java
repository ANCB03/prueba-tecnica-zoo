package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.zoo.dtos.AnimalDto;
import org.pruebatecnica.zoo.entities.Animal;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    @Mapping(source = "idEspecie", target = "especie.idEspecie")
    Animal toEntity(AnimalDto animalDto);

    @Mapping(source = "especie.idEspecie", target = "idEspecie")
    AnimalDto toDto(Animal animal);

    List<AnimalDto> toAnimallist(List<Animal> entityList);
}
