package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {
    private int idComentario;

    @NotBlank(message = "se requiere el mensaje del comentario")
    private String mensaje;

    private LocalDateTime fecha;

    private String tipo;

    private boolean tablero;

    @Min(value = 1, message = "El campo idAutor debe ser mayor que 0")
    private int IdAutor;

    @Min(value = 1, message = "El campo idAnimal debe ser mayor que 0")
    private int idAnimal;

    private int idComentarioPadre;
}
