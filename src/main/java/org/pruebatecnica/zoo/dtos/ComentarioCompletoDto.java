package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioCompletoDto {
    private int idComentario;

    private String mensaje;

    private LocalDateTime fecha;

    private String tipo;

    private boolean tablero;

    private UsuarioResponse autor;

    private AnimalResponse animal;

    private int idComentarioPadre;

    private List<ComentarioResponseSinCom> comentariosHijos = new ArrayList<>();
}
