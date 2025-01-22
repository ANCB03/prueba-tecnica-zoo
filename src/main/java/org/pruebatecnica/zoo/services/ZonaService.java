package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.CantidadAnimalesResponse;
import org.pruebatecnica.zoo.dtos.ZonaCompletoDto;
import org.pruebatecnica.zoo.dtos.ZonaDto;

import java.util.List;

public interface ZonaService {
    public List<ZonaDto> listarZonas();

    public void guardar(ZonaDto zonaDto);

    public void eliminar(int id);

    public ZonaCompletoDto encontrarZonaById(int id);

    public ZonaDto editarZona(ZonaDto zonaDto);

    public CantidadAnimalesResponse cantidadAnimales(int idZona);
}
