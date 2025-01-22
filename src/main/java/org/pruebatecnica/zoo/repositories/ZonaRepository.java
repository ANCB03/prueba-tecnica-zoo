package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Comentario;
import org.pruebatecnica.zoo.entities.Usuario;
import org.pruebatecnica.zoo.entities.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {
    @Query(
            value = "SELECT * FROM zona u where u.nombre_zona = :nombre",
            nativeQuery = true
    )
    Optional<Zona> findByNombre(String nombre);

    @Query(
            value = "SELECT * FROM zona c where c.nombre_zona ILIKE %:palabra%",
            nativeQuery = true
    )
    List<Zona> findByPalabra(String palabra);
}
