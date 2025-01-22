package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.dtos.AnimalDto;
import org.pruebatecnica.zoo.services.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/animal")
@CrossOrigin
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService service;

    private Map<String,Object> response = new HashMap<>();

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> AnimalList(){
        return new ResponseEntity<>(service.listarAnimaless(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findAnimal(@PathVariable int id) {
        return new ResponseEntity<>(service.encontrarAnimalById(id), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> saveAnimal(@Valid @RequestBody AnimalDto animalDto) {
        response.clear();
        service.guardar(animalDto);
        response.put("message","Animal guardado");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable int id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> editAnimal(@Valid @RequestBody AnimalDto animalDto) {
        service.editarAnimal(animalDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/fecha/{fecha}"})
    public ResponseEntity<?> findAnimalesFecha(@PathVariable String fecha) {
        return new ResponseEntity<>(service.animalesPorFecha(fecha), HttpStatus.OK);
    }
}
