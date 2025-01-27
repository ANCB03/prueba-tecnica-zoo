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
public class EspecieResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idEspecie;


    private String nombreEspecie;


    private ZonaDto zona;
}
