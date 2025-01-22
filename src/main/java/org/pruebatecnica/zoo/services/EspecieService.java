package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.*;

import java.util.List;

public interface EspecieService {
    public List<EspecieResponse> listarEspecies();

    public void guardar(EspecieDto especieDto);

    public void eliminar(int id);

    public EspecieCompletoDto encontrarEspecieById(int id);

    public EspecieDto editarEspecie(EspecieDto especieDto);

    public CantidadAnimalesEspecie cantidadAnimales(int idEspecie);
}
