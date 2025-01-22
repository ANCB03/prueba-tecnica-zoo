package org.pruebatecnica.zoo.implement;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.entities.Animal;
import org.pruebatecnica.zoo.entities.Comentario;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Zona;
import org.pruebatecnica.zoo.exceptions.BadRequestException;
import org.pruebatecnica.zoo.mappers.AnimalResponseMapper;
import org.pruebatecnica.zoo.mappers.ComentarioResponseMapper;
import org.pruebatecnica.zoo.mappers.EspecieResponseMapper;
import org.pruebatecnica.zoo.mappers.ZonaMapper;
import org.pruebatecnica.zoo.repositories.AnimalRepository;
import org.pruebatecnica.zoo.repositories.ComentarioRepository;
import org.pruebatecnica.zoo.repositories.EspecieRepository;
import org.pruebatecnica.zoo.repositories.ZonaRepository;
import org.pruebatecnica.zoo.services.BusquedaService;
import org.pruebatecnica.zoo.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BusquedaImplement implements BusquedaService {

    private final ComentarioRepository comentarioRepository;

    private final AnimalRepository animalRepository;

    private final EspecieRepository especieRepository;

    private final ZonaRepository zonaRepository;

    private final ComentarioResponseMapper comentarioResponseMapper;

    private final AnimalResponseMapper animalResponseMapper;

    private final EspecieResponseMapper especieResponseMapper;

    private final MessageUtil messageUtil;

    private final ZonaMapper zonaMapper;
    @Transactional
    @Override
    public Map<String, Object> busquedaPalabra(String palabra) {
        if(!esUnaSolaPalabra(palabra)){
            throw new BadRequestException(messageUtil.getMessage("NoEsPalabra", null, Locale.getDefault()));
        }
        return busquedaGeneral(palabra);
    }

    @Override
    public Map<String, Object> busquedaGeneralTexto(String texto) {
        return busquedaGeneral(texto);
    }

    private Map<String, Object> busquedaGeneral(String texto){
        List<Comentario> respuestas = comentarioRepository.findRespuestaByPalabra(texto);
        Map<String,Object> response = new TreeMap<>();
        int contador = 0;
        String resultado = "";
        if(!respuestas.isEmpty()){
            for(Comentario comentario: respuestas){
                contador++;
                resultado = "resultado "+ String.valueOf(contador);
                response.put(resultado, comentarioResponseMapper.toDto(comentario));
            }
        }
        List<Comentario> comentarios = comentarioRepository.findComentarioByPalabra(texto);
        if(!respuestas.isEmpty()){
            for(Comentario comentario: comentarios){
                contador++;
                resultado = "resultado "+ String.valueOf(contador);
                response.put(resultado, comentarioResponseMapper.toDto(comentario));
            }
        }
        List<Animal> animales = animalRepository.findByPalabra(texto);
        if(!animales.isEmpty()){
            for(Animal animal: animales){
                contador++;
                resultado = "resultado "+ String.valueOf(contador);
                response.put(resultado, animalResponseMapper.toDto(animal));
            }
        }
        List<Especie> especies = especieRepository.findByPalabra(texto);
        if(!especies.isEmpty()){
            for(Especie especie: especies){
                contador++;
                resultado = "resultado "+ String.valueOf(contador);
                response.put(resultado, especieResponseMapper.toDto(especie));
            }
        }
        List<Zona> zonas = zonaRepository.findByPalabra(texto);
        if(!zonas.isEmpty()){
            for(Zona zona: zonas){
                contador++;
                resultado = "resultado "+ String.valueOf(contador);
                response.put(resultado, zonaMapper.toDto(zona));
            }
        }
        return response;
    }
    private boolean esUnaSolaPalabra(String input) {
        if (input == null || input.isEmpty()) {
            return false; // No es válido si es nulo o vacío
        }
        // Verificar que no contenga espacios ni tabulaciones
        return !input.contains(" ") && !input.contains("\t");
    }
}
