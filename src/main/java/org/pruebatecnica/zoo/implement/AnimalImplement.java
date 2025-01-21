package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.AnimalCompletoDto;
import org.pruebatecnica.zoo.dtos.AnimalDto;
import org.pruebatecnica.zoo.entities.Animal;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.mappers.AnimalCompletoMapper;
import org.pruebatecnica.zoo.mappers.AnimalMapper;
import org.pruebatecnica.zoo.repositories.AnimalRepository;
import org.pruebatecnica.zoo.repositories.EspecieRepository;
import org.pruebatecnica.zoo.services.AnimalService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AnimalImplement implements AnimalService {

    private final AnimalRepository repository;

    private final EspecieRepository especieRepository;

    private final AnimalMapper animalMapper;

    private final AnimalCompletoMapper animalCompletoMapper;

    private final MessageUtil messageUtil;
    @Override
    public List<AnimalDto> listarAnimaless() {
        return animalMapper.toAnimallist(repository.findAll());
    }

    @Override
    public void guardar(AnimalDto animalDto) {
        ZoneId colombiaZone = ZoneId.of("America/Bogota");
        ZonedDateTime fechaHoraColombia = ZonedDateTime.now(colombiaZone);
        LocalDateTime fechaHoraActual = fechaHoraColombia.toLocalDateTime();
        animalDto.setFecha(fechaHoraActual);
        repository.save(animalMapper.toEntity(animalDto));
    }

    @Override
    public void eliminar(int id) {
        repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("AnimalNotFound", null, Locale.getDefault()))
        );
        repository.deleteById(id);
    }
    @Transactional
    @Override
    public AnimalCompletoDto encontrarAnimalById(int id) {
        return animalCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("AnimalNotFound", null, Locale.getDefault()))
        ));
    }
    @Transactional
    @Override
    public AnimalDto editarAnimal(AnimalDto animalDto) {
        Animal animal = repository.findById(animalDto.getIdAnimal()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("AnimalNotFound", null, Locale.getDefault()))
        );

        Especie especie = especieRepository.findById(animalDto.getIdEspecie()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("EspecieNotFound", null, Locale.getDefault()))
        );

        if(animalDto.getNombreAnimal() != null){
            animal.setNombreAnimal(animalDto.getNombreAnimal());
        }

        if(animalDto.getFecha() != null){
            animal.setFecha(animalDto.getFecha());
        }

        if(especie != null){
            animal.setEspecie(especie);
        }

        repository.save(animal);
        return animalMapper.toDto(animal);
    }
}
