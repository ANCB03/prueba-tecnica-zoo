package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query(
            value = "SELECT COUNT(c) FROM Comentario c WHERE c.id_comentario_padre IS NOT NULL",
            nativeQuery = true
    )
    long countWithResponses();

    @Query(
            value = "SELECT COUNT(c) FROM comentario c",
            nativeQuery = true
    )
    long countAllComentarios();

    @Query(
            value = "SELECT * FROM comentario c where c.mensaje ILIKE %:palabra%",
            nativeQuery = true
    )
    List<Comentario> findByPalabra(String palabra);

    @Query(
            value = "SELECT * FROM comentario c where c.mensaje ILIKE %:palabra% and c.tipo = 'RESPUESTA'",
            nativeQuery = true
    )
    List<Comentario> findRespuestaByPalabra(String palabra);

    @Query(
            value = "SELECT * FROM comentario c where c.mensaje ILIKE %:palabra% and c.tipo = 'COMENTARIO'",
            nativeQuery = true
    )
    List<Comentario> findComentarioByPalabra(String palabra);

    @Query(
            value = "SELECT * FROM comentario c where c.tablero = true",
            nativeQuery = true
    )
    List<Comentario> findComentarioByTablero();

    @Query(
            value = "SELECT * FROM comentario c where c.tablero = true and c.id_animal = :idAnimal",
            nativeQuery = true
    )
    List<Comentario> findComentariosByAnimal(int idAnimal);
}
