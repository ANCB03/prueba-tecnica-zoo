package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private int idUsuario;

    private String nombre;

    private String apellido;

    private String documento;

    private boolean estado;

    private String email;

    private RolDto rol;
}
