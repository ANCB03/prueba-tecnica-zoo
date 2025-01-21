package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZonaCompletoDto {
    private int idZona;

    @NotBlank(message = "se requiere el nombre de la zona")
    private String nombreZona;

    private List<EspecieSinZonaDto> especies = new ArrayList<>();
}
