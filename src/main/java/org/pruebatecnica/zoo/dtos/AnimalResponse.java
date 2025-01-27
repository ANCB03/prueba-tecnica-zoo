package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idAnimal;

    private String nombreAnimal;

    private LocalDate fecha;

    private EspecieResponse especie;
}
