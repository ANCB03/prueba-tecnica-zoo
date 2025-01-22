package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CantidadAnimalesEspecie {
    private int idEspecie;

    private String nombreEspecie;

    private int cantidadAnimales;
}
