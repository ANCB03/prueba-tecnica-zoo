package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimalService {
    public Page<AnimalResponse> listarAnimaless(int page, int size);

    public void guardar(AnimalDto animalDto);

    public void eliminar(int id);

    public AnimalCompletoDto encontrarAnimalById(int id);

    public AnimalCompletoDto editarAnimal(AnimalDto animalDto);

    public List<AnimalResponse> animalesPorFecha(String fecha);
}
