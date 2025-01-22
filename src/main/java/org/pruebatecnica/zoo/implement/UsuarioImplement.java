package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.UsuarioCompletoDto;
import org.pruebatecnica.zoo.dtos.UsuarioDto;
import org.pruebatecnica.zoo.dtos.UsuarioResponse;
import org.pruebatecnica.zoo.entities.Rol;
import org.pruebatecnica.zoo.entities.Usuario;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.UserDetailsMapper;
import org.pruebatecnica.zoo.mappers.UsuarioCompletoMapper;
import org.pruebatecnica.zoo.mappers.UsuarioMapper;
import org.pruebatecnica.zoo.mappers.UsuarioResponseMapper;
import org.pruebatecnica.zoo.repositories.RolRepository;
import org.pruebatecnica.zoo.repositories.UsuarioRepository;
import org.pruebatecnica.zoo.services.UsuarioService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioImplement implements UsuarioService, UserDetailsService {
    private final UsuarioRepository repository;

    private final RolRepository rolRepository;

    private final UsuarioMapper usuarioMapper;

    private final UsuarioResponseMapper usuarioResponseMapper;

    private final UsuarioCompletoMapper usuarioCompletoMapper;

    private final MessageUtil messageUtil;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioResponseMapper.toUsuariolist(repository.findAll());
    }

    @Override
    public void guardar(UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioExistente = repository.findByDocumento(usuarioDto.getDocumento());
        rolRepository.findById(usuarioDto.getIdRol()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("RolNotFound",null, Locale.getDefault()))
        );
        if (!usuarioExistente.isEmpty()) {
            throw new WithReferencesException(messageUtil.getMessage("UsuarioWithDocument", null, Locale.getDefault()));
        }
        Optional<Usuario> usuarioExistenteEmail = repository.findByEmail(usuarioDto.getEmail());
        if (!usuarioExistenteEmail.isEmpty()) {
            throw new WithReferencesException(messageUtil.getMessage("UsuarioWithEmail", null, Locale.getDefault()));
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }
    @Transactional
    @Override
    public void eliminar(int id) {
        Usuario usuario = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("UsuarioNotFound", null, Locale.getDefault()))
        );
        if (!usuario.getComentarios().isEmpty()) {
            throw new WithReferencesException(messageUtil.getMessage("UsuarioWithComentarios", null, Locale.getDefault()));
        }
        repository.deleteById(id);
    }
    @Transactional
    @Override
    public UsuarioCompletoDto encontrarUsuarioById(int id) {
        return usuarioCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("UsuarioNotFound", null, Locale.getDefault()))
        ));
    }
    @Transactional
    @Override
    public UsuarioDto editarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = repository.findById(usuarioDto.getIdUsuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("UsuarioNotFound",null, Locale.getDefault()))
        );

        if (usuarioDto.getIdRol() != 0){
            Rol rol = rolRepository.findById(usuarioDto.getIdRol()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("RolNotFound", null, Locale.getDefault()))
            );
            usuario.setRol(rol);
        }

        if (!usuarioDto.getNombre().isEmpty())
            usuario.setNombre(usuarioDto.getNombre());


        if (!usuarioDto.getApellido().isEmpty())
            usuario.setApellido(usuarioDto.getApellido());


        if (!usuarioDto.getDocumento().isEmpty())
            usuario.setDocumento(usuarioDto.getDocumento());

        usuario.setEstado(usuarioDto.isEstado());

        if (usuarioDto.getEmail() != null)
            usuario.setEmail(usuarioDto.getEmail());

        repository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Usuario retrievedUser = repository.findByEmail(username).get();
        if (retrievedUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return UserDetailsMapper.build(retrievedUser);
    }
}
