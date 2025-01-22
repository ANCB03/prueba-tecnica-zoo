package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.ComentarioDto;
import org.pruebatecnica.zoo.services.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> ComentarioList(){
        return new ResponseEntity<>(service.listarComentarios(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findComentario(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarComentarioById(id), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveComentario(@Valid @RequestBody ComentarioDto comentarioDto) {
        response.clear();
        service.guardar(comentarioDto);
        response.put("message","Comentario guardado");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComentario(@PathVariable int id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editComentario(@Valid @RequestBody ComentarioDto comentarioDto) {
        service.editarComentario(comentarioDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/porcentaje"})
    public ResponseEntity<?> findPorcentaje() {
        return new ResponseEntity<>(service.calcularPromedioComentariosRespuestas(), HttpStatus.OK);
    }
}
