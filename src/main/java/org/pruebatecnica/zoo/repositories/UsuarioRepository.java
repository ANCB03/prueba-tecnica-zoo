package org.pruebatecnica.zoo.repositories;

import org.pruebatecnica.zoo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(
            value = "SELECT * FROM usuario u where u.email = :email",
            nativeQuery = true
    )
    Optional<Usuario> findByEmail(String email);

    @Query(
            value = "SELECT * FROM usuario u where u.documento = :documento",
            nativeQuery = true
    )
    Optional<Usuario> findByDocumento(String documento);
}
