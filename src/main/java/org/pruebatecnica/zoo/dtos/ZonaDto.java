package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZonaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idZona;

    @NotBlank(message = "se requiere el nombre de la zona")
    private String nombreZona;
}
