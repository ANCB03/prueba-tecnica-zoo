package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.AnimalCompletoDto;
import org.pruebatecnica.zoo.dtos.AnimalDto;
import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;

import java.util.List;

public interface AnimalService {
    public List<AnimalDto> listarAnimaless();

    public void guardar(AnimalDto animalDto);

    public void eliminar(int id);

    public AnimalCompletoDto encontrarAnimalById(int id);

    public AnimalDto editarAnimal(AnimalDto animalDto);
}
