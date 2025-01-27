package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolCompletoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idRol;

    @NotBlank(message = "Se requiere el nombre del rol")
    private String nombre;

    private List<UsuarioSinRolDto> usuarios = new ArrayList<>();
}
