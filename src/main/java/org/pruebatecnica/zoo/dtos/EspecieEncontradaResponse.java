package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecieEncontradaResponse {
    private String zona;

    private String especie;
}
