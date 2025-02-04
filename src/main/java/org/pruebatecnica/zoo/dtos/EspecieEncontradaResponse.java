package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecieEncontradaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String zona;

    private String especie;
}
