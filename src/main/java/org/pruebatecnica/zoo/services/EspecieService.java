package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.*;
import org.springframework.data.domain.Page;

public interface EspecieService {
    public Page<EspecieResponse> listarEspecies(int page, int size);

    public void guardar(EspecieDto especieDto);

    public void eliminar(int id);

    public EspecieCompletoDto encontrarEspecieById(int id);

    public EspecieCompletoDto editarEspecie(EspecieDto especieDto);

    public CantidadAnimalesEspecie cantidadAnimales(int idEspecie);
}
