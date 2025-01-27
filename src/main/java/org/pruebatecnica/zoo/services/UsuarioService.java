package org.pruebatecnica.zoo.services;

import org.pruebatecnica.zoo.dtos.UsuarioCompletoDto;
import org.pruebatecnica.zoo.dtos.UsuarioDto;
import org.pruebatecnica.zoo.dtos.UsuarioResponse;
import org.springframework.data.domain.Page;


public interface UsuarioService {
    public Page<UsuarioResponse> listarUsuarios(int page, int size);

    public void guardar(UsuarioDto usuarioDto);

    public void eliminar(int id);

    public UsuarioCompletoDto encontrarUsuarioById(int id);

    public UsuarioCompletoDto editarUsuario(UsuarioDto usuarioDto);
}
