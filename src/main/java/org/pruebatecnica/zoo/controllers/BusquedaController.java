package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.services.BusquedaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/busqueda")
@CrossOrigin
@RequiredArgsConstructor
public class BusquedaController {
    private final BusquedaService busquedaService;

    @GetMapping({"/"})
    public ResponseEntity<?> findComentario(@RequestParam String palabra) {
        return new ResponseEntity<>(busquedaService.busqueda(palabra), HttpStatus.OK);
    }
}
