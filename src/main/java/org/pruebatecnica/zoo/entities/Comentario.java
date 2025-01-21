package org.pruebatecnica.zoo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comentario")
public class Comentario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @Column(length = 400, nullable = false)
    private String mensaje;

    private LocalDateTime fecha;

    @Column(length = 15, nullable = false)
    private String tipo;

    private boolean tablero;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "idAnimal")
    private Animal animal;

    @ManyToOne
    @JoinColumn(updatable = false ,name = "idComentarioPadre")
    private Comentario comentarioPadre;
}
