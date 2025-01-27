package org.pruebatecnica.zoo.services;


import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;
import org.springframework.data.domain.Page;

public interface RolService {
    public Page<RolDto> listarRoles(int page, int size);

    public void guardar(RolDto rolDto);

    public void eliminar(int id);

    public RolCompletoDto encontrarRolById(int id);

    public RolCompletoDto editarRol(RolDto rolDto);
}
