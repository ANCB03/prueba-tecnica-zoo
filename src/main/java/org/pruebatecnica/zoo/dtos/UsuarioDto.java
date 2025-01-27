package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idUsuario;

    @NotBlank(message = "Se requiere el nombre del usuario")
    private String nombre;

    @NotBlank(message = "Se requiere el apellido del usuario")
    private String apellido;

    @NotBlank(message = "Se requiere el documento del usuario")
    @Pattern(
            regexp = "\\d{1,15}$",
            message = "El documento debe contener solo números y tener un máximo de 15 caracteres"
    )
    private String documento;

    private boolean estado;

    @NotBlank(message = "Se requiere el email del usuario")
    @Email(message = "El correo electrónico no tiene un formato válido")
    private String email;

    @NotBlank(message = "Se requiere el password del usuario")
    private String password;

    @Min(value = 1, message = "El campo id_rol debe ser mayor que 0")
    private int idRol;
}
