package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.*;

import java.util.List;

public interface AnimalService {
    public List<AnimalResponse> listarAnimaless();

    public void guardar(AnimalDto animalDto);

    public void eliminar(int id);

    public AnimalCompletoDto encontrarAnimalById(int id);

    public AnimalDto editarAnimal(AnimalDto animalDto);

    public List<AnimalPorFechaResponse> animalesPorFecha(String fecha);
}
