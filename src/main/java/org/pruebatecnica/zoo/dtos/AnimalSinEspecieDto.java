package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSinEspecieDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idAnimal;

    @NotBlank(message = "se requiere el nombre del animal")
    private String nombreAnimal;

    private LocalDate fecha;
}
