package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.CantidadAnimalesResponse;
import org.pruebatecnica.zoo.dtos.ZonaCompletoDto;
import org.pruebatecnica.zoo.dtos.ZonaDto;
import org.springframework.data.domain.Page;

public interface ZonaService {
    public Page<ZonaDto> listarZonas(int page, int size);

    public void guardar(ZonaDto zonaDto);

    public void eliminar(int id);

    public ZonaCompletoDto encontrarZonaById(int id);

    public ZonaCompletoDto editarZona(ZonaDto zonaDto);

    public CantidadAnimalesResponse cantidadAnimales(int idZona);
}
