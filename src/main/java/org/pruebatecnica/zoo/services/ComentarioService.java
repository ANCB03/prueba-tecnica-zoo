package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.ComentarioCompletoDto;
import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.dtos.ComentarioResponse;
import org.pruebatecnica.zoo.dtos.PorcentajeComentariosResponse;

import java.util.List;

public interface ComentarioService {
    public List<ComentarioResponse> listarComentarios();

    public void guardar(ComentarioDto comentarioDto);

    public void eliminar(int id);

    public ComentarioCompletoDto encontrarComentarioById(int id);

    public ComentarioDto editarComentario(ComentarioDto comentarioDto);

    public PorcentajeComentariosResponse calcularPromedioComentariosRespuestas();
}
