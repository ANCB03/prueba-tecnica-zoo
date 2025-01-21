package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZonaRequest {

    @NotBlank(message = "se requiere el nombre de la zona")
    private String nombreZona;

    @NotBlank(message = "se requiere el nuevo nombre de la zona")
    private String nuevoNombreZona;
}
