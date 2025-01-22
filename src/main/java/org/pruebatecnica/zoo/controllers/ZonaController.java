package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.AnimalDto;
import org.pruebatecnica.zoo.dtos.ZonaDto;
import org.pruebatecnica.zoo.dtos.ZonaRequest;
import org.pruebatecnica.zoo.services.AnimalService;
import org.pruebatecnica.zoo.services.ZonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/zona")
@CrossOrigin
@RequiredArgsConstructor
public class ZonaController {
    private final ZonaService service;

    private Map<String,Object> response = new HashMap<>();

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> ZonaList(){
        return new ResponseEntity<>(service.listarZonas(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findZona(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarZonaById(id), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveZona(@Valid @RequestBody ZonaDto zonaDto) {
        response.clear();
        service.guardar(zonaDto);
        response.put("message","Zona guardada");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteZona(@PathVariable int id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editZona(@Valid @RequestBody ZonaDto zonaDto) {
        service.editarZona(zonaDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/cantidad-animales/{id}"})
    public ResponseEntity<?> findCantidadAnimalesZona(@PathVariable int id) {
        return new ResponseEntity<>(service.cantidadAnimales(id), HttpStatus.OK);
    }
}
