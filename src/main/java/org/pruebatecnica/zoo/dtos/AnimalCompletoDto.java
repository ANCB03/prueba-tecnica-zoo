package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalCompletoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idAnimal;

    @NotBlank(message = "se requiere el nombre del animal")
    private String nombreAnimal;

    private LocalDate fecha;

    @Min(value = 0, message = "El valor debe ser mayor o igual a 0.")
    private int idEspecie;

    private List<ComentarioSinAnimalDto> comentarios = new ArrayList<>();
}
