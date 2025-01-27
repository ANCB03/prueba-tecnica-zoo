package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.ComentarioCompletoDto;
import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.dtos.ComentarioResponse;
import org.pruebatecnica.zoo.dtos.PorcentajeComentariosResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComentarioService {
    public Page<ComentarioResponse> listarComentarios(int page, int size);

    public void guardar(ComentarioDto comentarioDto);

    public void eliminar(int id);

    public ComentarioCompletoDto encontrarComentarioById(int id);

    public ComentarioCompletoDto editarComentario(ComentarioDto comentarioDto);

    public PorcentajeComentariosResponse calcularPromedioComentariosRespuestas();

    public ComentarioCompletoDto agregarATablero(int idComentario);

    public List<ComentarioDto> listarComentariosTablero();

    public List<ComentarioDto> listadoComentariosAnimal(int idAnimal);
}
