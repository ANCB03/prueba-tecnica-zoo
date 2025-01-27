package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecieSinZonaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Min(value = 0, message = "El valor debe ser mayor o igual a 0.")
    private int idEspecie;

    @NotBlank(message = "se requiere el nombre de la especie")
    private String nombreEspecie;

    private List<AnimalCompletoDto> animales = new ArrayList<>();
}
