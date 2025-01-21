package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EspecieRepository extends JpaRepository<Especie, Integer> {
    @Query(
            value = "SELECT * FROM zona u where u.nombre_especie = :nombre",
            nativeQuery = true
    )
    Optional<Usuario> findByNombre(String nombre);
}
