package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Comentario;
import org.pruebatecnica.zoo.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

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
}
