package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.*;
import org.pruebatecnica.zoo.entities.Animal;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.exceptions.BadRequestException;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.mappers.AnimalCompletoMapper;
import org.pruebatecnica.zoo.mappers.AnimalMapper;
import org.pruebatecnica.zoo.mappers.AnimalResponseMapper;
import org.pruebatecnica.zoo.mappers.ZonaMapper;
import org.pruebatecnica.zoo.repositories.AnimalRepository;
import org.pruebatecnica.zoo.repositories.EspecieRepository;
import org.pruebatecnica.zoo.services.AnimalService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalImplement implements AnimalService {

    private final AnimalRepository repository;

    private final EspecieRepository especieRepository;

    private final AnimalMapper animalMapper;

    private final AnimalResponseMapper animalResponseMapper;

    private final ZonaMapper zonaMapper;

    private final AnimalCompletoMapper animalCompletoMapper;

    private final MessageUtil messageUtil;
    @Override
    public List<AnimalResponse> listarAnimaless() {
        return animalResponseMapper.toAnimallist(repository.findAll());
    }

    @Override
    public void guardar(AnimalDto animalDto) {
        Optional<Especie> especieFound = especieRepository.findById(animalDto.getIdEspecie());
        if(especieFound.isEmpty()){
            throw new NotFoundException(messageUtil.getMessage("EspecieNotFound", null, Locale.getDefault()));
        }
        ZoneId colombiaZone = ZoneId.of("America/Bogota");
        ZonedDateTime fechaColombia = ZonedDateTime.now(colombiaZone);
        LocalDate fechaActual = fechaColombia.toLocalDate();
        animalDto.setFecha(fechaActual);
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

    @Override
    public List<AnimalResponse> animalesPorFecha(String fecha) {
        if (esFechaValida(fecha)){
            LocalDate fecha1 = LocalDate.parse(fecha);
        List<Animal> animales = repository.findByFecha(fecha1);
        if (!animales.isEmpty()){
            return animalResponseMapper.toAnimallist(animales);
        }else{
            throw new NotFoundException(messageUtil.getMessage("AnimalPorFechaNotFound", null, Locale.getDefault()));
        }
        }else{
            throw new BadRequestException(messageUtil.getMessage("BadRequestFecha",null, Locale.getDefault()));
        }
    }

    public boolean esFechaValida(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(fecha, formatter);
            return true; // La fecha es válida
        } catch (DateTimeParseException e) {
            return false; // La fecha no es válida
        }
    }
}
