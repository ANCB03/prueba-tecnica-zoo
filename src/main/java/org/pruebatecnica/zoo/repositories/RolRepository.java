package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Especie;
import org.pruebatecnica.zoo.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query(
            value = "SELECT * FROM rol u where u.nombre = :nombre",
            nativeQuery = true
    )
    Optional<Rol> findByNombre(String nombre);
}
