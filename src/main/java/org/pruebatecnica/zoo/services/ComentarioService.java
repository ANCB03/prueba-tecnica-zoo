package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.dtos.RolCompletoDto;
import org.pruebatecnica.zoo.dtos.RolDto;

import java.util.List;

public interface ComentarioService {
    public List<ComentarioDto> listarComentarios();

    public void guardar(ComentarioDto comentarioDto);

    public void eliminar(int id);

    public ComentarioDto encontrarComentarioById(int id);

    public ComentarioDto editarComentario(ComentarioDto comentarioDto);
}
