package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;

import org.pruebatecnica.zoo.dtos.RolDto;
import org.pruebatecnica.zoo.services.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rol")
@CrossOrigin
@RequiredArgsConstructor
public class RolController {
    private final RolService service;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> RolList(){
        return new ResponseEntity<>(service.listarRoles(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findRol(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarRolById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveRol(@Valid @RequestBody RolDto rolDto) {
        response.clear();
        service.guardar(rolDto);
        response.put("message","Rol guardado");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable int id) {
        response.clear();
        service.eliminar(id);
        response.put("message","Rol eliminado");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editParqueadero(@Valid @RequestBody RolDto rolDto) {
        response.clear();
        RolDto rolDto1 = service.editarRol(rolDto);
        response.put("message", "Rol editado");
        response.put("rol", rolDto1);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
