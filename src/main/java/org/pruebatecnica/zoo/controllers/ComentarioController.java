package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.services.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comentario")
@CrossOrigin
@RequiredArgsConstructor
public class ComentarioController {
    private final ComentarioService service;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> ComentarioList(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(service.listarComentarios(page,size), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findComentario(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarComentarioById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLEADO')")
    @PostMapping("/")
    public ResponseEntity<?> saveComentario(@Valid @RequestBody ComentarioDto comentarioDto) {
        response.clear();
        service.guardar(comentarioDto);
        response.put("message","Comentario guardado");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComentario(@PathVariable int id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editComentario(@Valid @RequestBody ComentarioDto comentarioDto) {
        service.editarComentario(comentarioDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/porcentaje"})
    public ResponseEntity<?> findPorcentaje() {
        return new ResponseEntity<>(service.calcularPromedioComentariosRespuestas(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/tablero")
    public ResponseEntity<?> comentarioTablero(@PathVariable int id) {
        service.agregarATablero(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/tablero"})
    public ResponseEntity<?> findComentariosTablero() {
        return new ResponseEntity<>(service.listarComentariosTablero(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/tablero/animal/{id}"})
    public ResponseEntity<?> findComentariosTableroAnimal(@PathVariable int id) {
        return new ResponseEntity<>(service.listadoComentariosAnimal(id), HttpStatus.OK);
    }
}
