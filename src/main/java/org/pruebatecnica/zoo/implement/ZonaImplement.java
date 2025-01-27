package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.CantidadAnimalesResponse;
import org.pruebatecnica.zoo.dtos.ZonaCompletoDto;
import org.pruebatecnica.zoo.dtos.ZonaDto;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Zona;
import org.pruebatecnica.zoo.exceptions.BadRequestException;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.ZonaCompletoMapper;
import org.pruebatecnica.zoo.mappers.ZonaMapper;
import org.pruebatecnica.zoo.repositories.ZonaRepository;
import org.pruebatecnica.zoo.services.ZonaService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Cacheable(value = "zonaPageCache", key = "#page + '-' + #size")
    @Override
    public Page<ZonaDto> listarZonas(int page, int size) {
        if(page <= 0){
            throw  new BadRequestException(messageUtil.getMessage("ErrorPage", null, Locale.getDefault()));
        }else {
            page--;
        }
        if(size <= 0){
            throw  new BadRequestException(messageUtil.getMessage("ErrorSize", null, Locale.getDefault()));
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Zona> zonas = repository.findAll(pageable);
        return zonas.map(zonaMapper::toDto);
    }

    @CacheEvict(value = "zonaPageCache", allEntries = true)
    @Override
    public void guardar(ZonaDto zonaDto) {
        Optional<Zona> zonaFound = repository.findByNombre(zonaDto.getNombreZona());
        if(zonaFound.isPresent()){
            throw new WithReferencesException(messageUtil.getMessage("ZonaExists", null, Locale.getDefault()));
        }
        repository.save(zonaMapper.toEntity(zonaDto));
    }

    @Transactional
    @CacheEvict(value = {"zonaCache", "zonaPageCache"}, key = "#id", allEntries = true)
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
    @Cacheable(value = "zonaCache", key = "#id")
    @Override
    public ZonaCompletoDto encontrarZonaById(int id) {
        return zonaCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        ));
    }

    @CachePut(value = "zonaCache", key = "#zonaDto.idZona")
    @CacheEvict(value = "zonaPageCache", allEntries = true)
    @Override
    public ZonaCompletoDto editarZona(ZonaDto zonaDto) {
        Zona zona = repository.findById(zonaDto.getIdZona()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ZonaNotFound", null, Locale.getDefault()))
        );
        if(zonaDto.getNombreZona() != null && !zonaDto.getNombreZona().equals(zona.getNombreZona())){
            if(repository.findByNombre(zonaDto.getNombreZona()).isPresent()){
                throw new WithReferencesException(messageUtil.getMessage("ZonaExists", null, Locale.getDefault()));
            }
            zona.setNombreZona(zonaDto.getNombreZona());
        }
        repository.save(zona);
        return zonaCompletoMapper.toDto(zona);
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
