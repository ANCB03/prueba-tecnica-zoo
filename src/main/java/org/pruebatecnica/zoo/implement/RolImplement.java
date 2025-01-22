package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;
import org.pruebatecnica.zoo.entities.Rol;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.RolCompletoMapper;
import org.pruebatecnica.zoo.mappers.RolMapper;
import org.pruebatecnica.zoo.repositories.RolRepository;
import org.pruebatecnica.zoo.services.RolService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolImplement implements RolService {

    private final RolRepository repository;

    private final RolMapper rolMapper;

    private final RolCompletoMapper rolCompletoMapper;

    private final MessageUtil messageUtil;

    @Override
    public List<RolDto> listarRoles() {
        return rolMapper.toRollist(repository.findAll());
    }

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
    @Override
    public RolCompletoDto encontrarRolById(int id) {
        return rolCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("RolNotFound", null, Locale.getDefault()))
        ));
    }

    @Override
    public RolDto editarRol(RolDto rolDto) {
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
        return rolMapper.toDto(rol);
    }
}
