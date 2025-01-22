package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.UsuarioDto;
import org.pruebatecnica.zoo.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> UsuarioList(){
        return new ResponseEntity<>(service.listarUsuarios(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findUsuario(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarUsuarioById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        response.clear();
        service.guardar(usuarioDto);
        response.put("message","Usuario guardado");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        service.editarUsuario(usuarioDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
