package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseSinCom {
    private int idComentario;

    @NotBlank(message = "se requiere el mensaje del comentario")
    private String mensaje;

    private LocalDateTime fecha;

    private String tipo;

    private boolean tablero;

    private UsuarioResponse autor;

    private AnimalResponse animal;
}
