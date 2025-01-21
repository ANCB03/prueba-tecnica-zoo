package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Usuario;
import org.pruebatecnica.zoo.entities.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {
    @Query(
            value = "SELECT * FROM zona u where u.nombre_zona = :nombre",
            nativeQuery = true
    )
    Optional<Usuario> findByNombre(String nombre);
}
