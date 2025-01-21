package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
