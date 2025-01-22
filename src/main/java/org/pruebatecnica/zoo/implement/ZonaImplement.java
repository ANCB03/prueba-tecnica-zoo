package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.CantidadAnimalesResponse;
import org.pruebatecnica.zoo.dtos.ZonaCompletoDto;
import org.pruebatecnica.zoo.dtos.ZonaDto;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Zona;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.ZonaCompletoMapper;
import org.pruebatecnica.zoo.mappers.ZonaMapper;
import org.pruebatecnica.zoo.repositories.ZonaRepository;
import org.pruebatecnica.zoo.services.ZonaService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZonaImplement implements ZonaService {
    private final ZonaRepository repository;

    private final ZonaMapper zonaMapper;

    private final ZonaCompletoMapper zonaCompletoMapper;

    private final MessageUtil messageUtil;
    @Override
    public List<ZonaDto> listarZonas() {
        return zonaMapper.toZonalist(repository.findAll());
    }

    @Override
    public void guardar(ZonaDto zonaDto) {
        Optional<Zona> zonaFound = repository.findByNombre(zonaDto.getNombreZona());
        if(zonaFound.isPresent()){
            throw new WithReferencesException(messageUtil.getMessage("ZonaExists", null, Locale.getDefault()));
        }
        repository.save(zonaMapper.toEntity(zonaDto));
    }

    @Transactional
    @Override
    public void eliminar(int id) {
        Zona zona = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        );
        if (!zona.getEspecies().isEmpty()) {
            int contador = 0;
            List<Especie> especies = zona.getEspecies();
            for(Especie especie: especies){
                if(!especie.getAnimales().isEmpty()){
                    contador++;
                    break;
                }
            }
            if(contador > 0) throw new WithReferencesException(messageUtil.getMessage("ZonaWithEspecies", null, Locale.getDefault()));
        }
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public ZonaCompletoDto encontrarZonaById(int id) {
        return zonaCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        ));
    }

    @Override
    public ZonaDto editarZona(ZonaDto zonaDto) {
        Zona zona = repository.findById(zonaDto.getIdZona()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        );
        if(zonaDto.getNombreZona() != null){
            if(!repository.findByNombre(zonaDto.getNombreZona()).isEmpty()){
                throw new WithReferencesException(messageUtil.getMessage("ZonaExists", null, Locale.getDefault()));
            }
            zona.setNombreZona(zonaDto.getNombreZona());
        }
        repository.save(zona);
        return zonaMapper.toDto(zona);
    }
    @Transactional
    @Override
    public CantidadAnimalesResponse cantidadAnimales(int idZona) {
        Zona zona = repository.findById(idZona) .orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        );
        CantidadAnimalesResponse cantidadAnimalesResponse = new CantidadAnimalesResponse();
        if (!zona.getEspecies().isEmpty()) {
            int contador = 0;
            List<Especie> especies = zona.getEspecies();
            for(Especie especie: especies){
                if(!especie.getAnimales().isEmpty()){
                    contador += especie.getAnimales().size();
                }
            }
            cantidadAnimalesResponse.setIdZona(idZona);
            cantidadAnimalesResponse.setNombreZona(zona.getNombreZona());
            cantidadAnimalesResponse.setCantidadAnimales(contador);
        }
        return cantidadAnimalesResponse;
    }
}
