package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.CantidadAnimalesEspecie;
import org.pruebatecnica.zoo.dtos.EspecieCompletoDto;
import org.pruebatecnica.zoo.dtos.EspecieDto;
import org.pruebatecnica.zoo.dtos.EspecieResponse;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Zona;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.EspecieCompletoMapper;
import org.pruebatecnica.zoo.mappers.EspecieMapper;
import org.pruebatecnica.zoo.mappers.EspecieResponseMapper;
import org.pruebatecnica.zoo.repositories.EspecieRepository;
import org.pruebatecnica.zoo.repositories.ZonaRepository;
import org.pruebatecnica.zoo.services.EspecieService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecieImplement implements EspecieService {
    private final EspecieRepository repository;

    private final ZonaRepository zonaRepository;

    private final EspecieMapper especieMapper;

    private final EspecieResponseMapper especieResponseMapper;

    private final EspecieCompletoMapper especieCompletoMapper;

    private final MessageUtil messageUtil;
    @Override
    public List<EspecieResponse> listarEspecies() {
        return especieResponseMapper.toEspecielist(repository.findAll());
    }

    @Override
    public void guardar(EspecieDto especieDto) {
                Optional<Especie> especieFound = repository.findByNombre(especieDto.getNombreEspecie());
                if (especieFound.isPresent()){
                    throw new WithReferencesException(messageUtil.getMessage("EspecieExists",null, Locale.getDefault()));
                }
                Especie especie = new Especie();
                if(especieDto.getIdZona() != 0){
                    Zona zona = zonaRepository.findById(especieDto.getIdZona()).orElseThrow(
                            () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
                    );
                    especie.setZona(zona);
                    especie.setNombreEspecie(especieDto.getNombreEspecie());
                    repository.save(especie);
                }
    }
    @Transactional
    @Override
    public void eliminar(int id) {
        Especie especie = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("EspecieNotFound", null, Locale.getDefault()))
        );
        if(!especie.getAnimales().isEmpty()){
            repository.deleteById(id);
        }else{
            throw new WithReferencesException(messageUtil.getMessage("EspecieWithAnimals", null, Locale.getDefault()));
        }
    }
    @Transactional
    @Override
    public EspecieCompletoDto encontrarEspecieById(int id) {
        return especieCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("EspecieNotFound", null, Locale.getDefault()))
        ));
    }
    @Transactional
    @Override
    public EspecieDto editarEspecie(EspecieDto especieDto) {
        Especie especie = repository.findById(especieDto.getIdEspecie()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("EspecieNotFound", null, Locale.getDefault()))
        );

        if(especieDto.getNombreEspecie() != null){
            especie.setNombreEspecie(especieDto.getNombreEspecie());
        }

        Zona zona = zonaRepository.findById(especieDto.getIdZona()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        );

        if(zona != null){
            especie.setZona(zona);
        }
        repository.save(especie);
        return especieMapper.toDto(especie);
    }
    @Transactional
    @Override
    public CantidadAnimalesEspecie cantidadAnimales(int idEspecie) {
        Especie especie = repository.findById(idEspecie) .orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("EspecieNotFound", null, Locale.getDefault()))
        );
        CantidadAnimalesEspecie cantidadAnimalesResponse = new CantidadAnimalesEspecie();
        cantidadAnimalesResponse.setIdEspecie(idEspecie);
        cantidadAnimalesResponse.setNombreEspecie(especie.getNombreEspecie());
        if (!especie.getAnimales().isEmpty()) {
            cantidadAnimalesResponse.setCantidadAnimales(especie.getAnimales().size());
        }
        return cantidadAnimalesResponse;
    }
}
