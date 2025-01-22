package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Animal;
import org.pruebatecnica.zoo.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findByFecha(LocalDate fecha);

    @Query(
            value = "SELECT * FROM animal c where c.nombre_animal ILIKE %:palabra%",
            nativeQuery = true
    )
    List<Animal> findByPalabra(String palabra);
}
