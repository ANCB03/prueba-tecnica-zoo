package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.ComentarioCompletoDto;
import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.dtos.ComentarioResponse;
import org.pruebatecnica.zoo.dtos.PorcentajeComentariosResponse;
import org.pruebatecnica.zoo.entities.Animal;
import org.pruebatecnica.zoo.entities.Comentario;
import org.pruebatecnica.zoo.entities.Usuario;
import org.pruebatecnica.zoo.exceptions.BadRequestException;
import org.pruebatecnica.zoo.exceptions.NotFoundException;
import org.pruebatecnica.zoo.exceptions.WithReferencesException;
import org.pruebatecnica.zoo.mappers.*;
import org.pruebatecnica.zoo.repositories.AnimalRepository;
import org.pruebatecnica.zoo.repositories.ComentarioRepository;
import org.pruebatecnica.zoo.repositories.UsuarioRepository;
import org.pruebatecnica.zoo.services.ComentarioService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ComentarioImplement implements ComentarioService {
    private final ComentarioRepository repository;

    private final AnimalRepository animalRepository;

    private final UsuarioRepository usuarioRepository;

    private final ComentarioMapper comentarioMapper;

    private final ComentarioCompletoMapper comentarioCompletoMapper;

    private final ComentarioResponseMapper comentarioResponseMapper;

    private final MessageUtil messageUtil;
    @Cacheable(value = "comentarioPageCache", key = "#page + '-' + #size")
    @Override
    public Page<ComentarioResponse> listarComentarios(int page, int size) {
        if(page <= 0){
            throw  new BadRequestException(messageUtil.getMessage("ErrorPage", null, Locale.getDefault()));
        }else {
            page--;
        }
        if(size <= 0){
            throw  new BadRequestException(messageUtil.getMessage("ErrorSize", null, Locale.getDefault()));
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Comentario> comentarios = repository.findAll(pageable);
        return comentarios.map(comentarioResponseMapper::toDto);
    }

    @CacheEvict(value = "comentarioPageCache", allEntries = true)
    @Override
    public void guardar(ComentarioDto comentarioDto) {
            Comentario comentario = new Comentario();
            Usuario usuario = usuarioRepository.findById(comentarioDto.getIdAutor()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("UsuarioNotFound", null, Locale.getDefault()))
            );
            Animal animal = animalRepository.findById(comentarioDto.getIdAnimal()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("AnimalNotFound", null, Locale.getDefault()))
            );
            ZoneId colombiaZone = ZoneId.of("America/Bogota");
            ZonedDateTime fechaHoraColombia = ZonedDateTime.now(colombiaZone);
            LocalDateTime fechaHoraActual = fechaHoraColombia.toLocalDateTime();
            comentario.setMensaje(comentarioDto.getMensaje());
            comentario.setFecha(fechaHoraActual);
            comentario.setTablero(false);
            comentario.setAutor(usuario);
            comentario.setAnimal(animal);
            if(comentarioDto.getIdComentarioPadre() == 0){
                comentario.setComentarioPadre(null);
                comentario.setTipo("COMENTARIO");
            }else{
                Comentario comentarioPadre = repository.findById(comentarioDto.getIdComentarioPadre()).orElseThrow(
                        () -> new NotFoundException(messageUtil.getMessage("ComentarioPadreNotFound", null, Locale.getDefault()))
                );
                comentario.setComentarioPadre(comentarioPadre);
                comentario.setTipo("RESPUESTA");
            }
            repository.save(comentario);
    }
    @Transactional
    @CacheEvict(value = {"comentarioCache", "comentarioPageCache"}, key = "#id", allEntries = true)
    @Override
    public void eliminar(int id) {
        Comentario comentario = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ComentarioNotFound", null, Locale.getDefault()))
        );
        if(comentario.getComentarioPadre() != null){
            throw new WithReferencesException(messageUtil.getMessage("ComentarioWithRespuesta", null, Locale.getDefault()));
        }
        repository.deleteById(id);
    }
    @Transactional
    @Cacheable(value = "comentarioCache", key = "#id")
    @Override
    public ComentarioCompletoDto encontrarComentarioById(int id) {
        return comentarioCompletoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ComentarioNotFound", null, Locale.getDefault()))
        ));
    }
    @Transactional
    @CachePut(value = "comentarioCache", key = "#comentarioDto.idComentario")
    @CacheEvict(value = "comentarioPageCache", allEntries = true)
    @Override
    public ComentarioCompletoDto editarComentario(ComentarioDto comentarioDto) {
        Comentario comentario = repository.findById(comentarioDto.getIdComentario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ComentarioNotFound", null, Locale.getDefault()))
        );

        if(comentarioDto.getMensaje() != null){
            comentario.setMensaje(comentarioDto.getMensaje());
        }

        repository.save(comentario);
        return comentarioCompletoMapper.toDto(comentario);
    }
    @Transactional
    @Override
    public PorcentajeComentariosResponse calcularPromedioComentariosRespuestas() {
        PorcentajeComentariosResponse porcentajeComentariosResponse = new PorcentajeComentariosResponse();
        long totalComentarios = repository.countAllComentarios();
        long comentariosConRespuestas = repository.countWithResponses();

        if (totalComentarios == 0) {
            porcentajeComentariosResponse.setPorcentajeComentarios(0.0);
            return porcentajeComentariosResponse;
        }
        porcentajeComentariosResponse.setPorcentajeComentarios((double) comentariosConRespuestas / totalComentarios * 100);
        return porcentajeComentariosResponse;
    }

    @CachePut(value = "comentarioCache", key = "#comentarioDto.idComentario")
    @CacheEvict(value = "comentarioPageCache", allEntries = true)
    @Override
    public ComentarioCompletoDto agregarATablero(int idComentario) {
        Comentario comentario = repository.findById(idComentario).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ComentarioNotFound", null, Locale.getDefault()))
        );
        comentario.setTablero(!comentario.isTablero());
        repository.save(comentario);
        return comentarioCompletoMapper.toDto(comentario);
    }

    @Override
    public List<ComentarioDto> listarComentariosTablero() {
        return comentarioMapper.toComentariolist(repository.findComentarioByTablero());
    }

    @Override
    public List<ComentarioDto> listadoComentariosAnimal(int idAnimal) {
        return comentarioMapper.toComentariolist(repository.findComentariosByAnimal(idAnimal));
    }
}
