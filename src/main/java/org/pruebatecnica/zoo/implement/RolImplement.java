package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;
import org.pruebatecnica.zoo.entities.Rol;
import org.pruebatecnica.zoo.exceptions.BadRequestException;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.RolCompletoMapper;
import org.pruebatecnica.zoo.mappers.RolMapper;
import org.pruebatecnica.zoo.repositories.RolRepository;
import org.pruebatecnica.zoo.services.RolService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolImplement implements RolService {

    private final RolRepository repository;

    private final RolMapper rolMapper;

    private final RolCompletoMapper rolCompletoMapper;

    private final MessageUtil messageUtil;

    @Cacheable(value = "rolPageCache", key = "#page + '-' + #size")
    @Override
    public Page<RolDto> listarRoles(int page, int size) {
        if(page <= 0){
            throw  new BadRequestException(messageUtil.getMessage("ErrorPage", null, Locale.getDefault()));
        }else {
            page--;
        }
        if(size <= 0){
            throw  new BadRequestException(messageUtil.getMessage("ErrorSize", null, Locale.getDefault()));
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Rol> roles = repository.findAll(pageable);
        return roles.map(rolMapper::toDto);
    }

    @CacheEvict(value = "rolPageCache", allEntries = true)
    @Override
    public void guardar(RolDto rolDto) {
        String nombre = rolDto.getNombre().toUpperCase();
        Optional<Rol> rolFound = repository.findByNombre(nombre);
        if(rolFound.isPresent()){
            throw new WithReferencesException(messageUtil.getMessage("RolExists",null, Locale.getDefault()));
        }
        rolDto.setNombre(nombre);
        repository.save(rolMapper.toEntity(rolDto));
    }
    @Transactional
    @CacheEvict(value = {"rolCache", "rolPageCache"}, key = "#id", allEntries = true)
    @Override
    public void eliminar(int id) {
        Rol rol = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("RolNotFound", null, Locale.getDefault()))
        );
        if (!rol.getUsuarios().isEmpty()) {
            throw new WithReferencesException(messageUtil.getMessage("RolWithUsuarios", null, Locale.getDefault()));
        }
        repository.deleteById(id);
    }
    @Transactional
    @Cacheable(value = "rolCache", key = "#id")
    @Override
    public RolCompletoDto encontrarRolById(int id) {
        return rolCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("RolNotFound", null, Locale.getDefault()))
        ));
    }

    @CachePut(value = "rolCache", key = "#rolDto.idRol")
    @CacheEvict(value = "rolPageCache", allEntries = true)
    @Override
    public RolCompletoDto editarRol(RolDto rolDto) {
        Rol rol = repository.findById(rolDto.getIdRol()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("RolNotFound", null, Locale.getDefault()))
        );
        String nombre = rolDto.getNombre().toUpperCase();
        Optional<Rol> rolFound = repository.findByNombre(nombre);
        if(rolFound.isPresent()){
            throw new WithReferencesException(messageUtil.getMessage("RolExists",null, Locale.getDefault()));
        }
        rolMapper.updateEntity(rolDto,rol);
        repository.save(rol);
        return rolCompletoMapper.toDto(rol);
    }
}
