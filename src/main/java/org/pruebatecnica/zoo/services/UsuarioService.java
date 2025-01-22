package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.UsuarioCompletoDto;
import org.pruebatecnica.zoo.dtos.UsuarioDto;
import org.pruebatecnica.zoo.dtos.UsuarioResponse;
import org.pruebatecnica.zoo.dtos.UsuarioSinPasswordDto;

import java.util.List;

public interface UsuarioService {
    public List<UsuarioResponse> listarUsuarios();

    public void guardar(UsuarioDto usuarioDto);

    public void eliminar(int id);

    public UsuarioCompletoDto encontrarUsuarioById(int id);

    public UsuarioDto editarUsuario(UsuarioDto usuarioDto);
}
