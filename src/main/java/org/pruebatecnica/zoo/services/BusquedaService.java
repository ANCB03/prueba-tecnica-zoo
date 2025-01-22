package org.pruebatecnica.zoo.services;

import java.util.Map;

public interface BusquedaService {
    public Map<String, Object> busquedaPalabra(String palabra);

    public Map<String, Object> busquedaGeneralTexto(String texto);
}
