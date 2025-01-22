package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalPorFechaResponse {
    private int idAnimal;

    private String nombre;

    private LocalDate fecha;

    private EspecieResponse especie;
}
