package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
