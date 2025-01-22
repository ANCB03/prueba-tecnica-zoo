package org.pruebatecnica.zoo.mappers;

import org.mapstruct.Mapper;
import org.pruebatecnica.zoo.dtos.AnimalResponse;
import org.pruebatecnica.zoo.entities.Animal;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalResponseMapper {
    Animal toEntity(AnimalResponse animalResppnse);

    AnimalResponse toDto(Animal animal);
    List<AnimalResponse> toAnimallist(List<Animal> entityList);
}
