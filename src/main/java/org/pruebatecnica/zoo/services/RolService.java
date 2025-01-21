package org.pruebatecnica.zoo.services;


import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;

import java.util.List;

public interface RolService {
    public List<RolDto> listarRoles();

    public void guardar(RolDto rolDto);

    public void eliminar(int id);

    public RolCompletoDto encontrarRolById(int id);

    public RolDto editarRol(RolDto rolDto);
}
