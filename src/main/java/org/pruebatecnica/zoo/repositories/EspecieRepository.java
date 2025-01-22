package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Comentario;
import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EspecieRepository extends JpaRepository<Especie, Integer> {
    @Query(
            value = "SELECT * FROM especie u where u.nombre_especie = :nombre",
            nativeQuery = true
    )
    Optional<Especie> findByNombre(String nombre);

    @Query(
            value = "SELECT * FROM especie c where c.nombre_especie ILIKE %:palabra%",
            nativeQuery = true
    )
    List<Especie> findByPalabra(String palabra);
}
