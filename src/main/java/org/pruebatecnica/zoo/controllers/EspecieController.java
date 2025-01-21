package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.EspecieDto;
import org.pruebatecnica.zoo.services.EspecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/especie")
@CrossOrigin
@RequiredArgsConstructor
public class EspecieController {
    private final EspecieService service;

    private Map<String,Object> response = new HashMap<>();

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> EspecieList(){
        return new ResponseEntity<>(service.listarEspecies(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findEspecie(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarEspecieById(id), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveEspecie(@Valid @RequestBody EspecieDto especieDto) {
        response.clear();
        service.guardar(especieDto);
        response.put("message","Especie guardada");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEspecie(@PathVariable int id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editEspecie(@Valid @RequestBody EspecieDto especieDto) {
        service.editarEspecie(especieDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
