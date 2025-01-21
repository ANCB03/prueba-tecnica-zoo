package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.EspecieCompletoDto;
import org.pruebatecnica.zoo.dtos.EspecieDto;
import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;

import java.util.List;

public interface EspecieService {
    public List<EspecieDto> listarEspecies();

    public void guardar(EspecieDto especieDto);

    public void eliminar(int id);

    public EspecieCompletoDto encontrarEspecieById(int id);

    public EspecieDto editarEspecie(EspecieDto especieDto);
}
