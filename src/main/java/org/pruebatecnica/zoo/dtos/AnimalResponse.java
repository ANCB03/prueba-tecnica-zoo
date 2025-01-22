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
public class AnimalResponse {
    private int idAnimal;

    private String nombreAnimal;

    private LocalDate fecha;

    private EspecieResponse especie;
}
