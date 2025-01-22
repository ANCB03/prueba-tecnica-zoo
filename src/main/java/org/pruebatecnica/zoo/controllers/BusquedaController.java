package org.pruebatecnica.zoo.controllers;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.zoo.services.BusquedaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/busqueda")
@CrossOrigin
@RequiredArgsConstructor
public class BusquedaController {
    private final BusquedaService busquedaService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/"})
    public ResponseEntity<?> busquedaPalabra(@RequestParam String palabra) {
        return new ResponseEntity<>(busquedaService.busquedaPalabra(palabra), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLEADO')")
    @GetMapping({"/texto"})
    public ResponseEntity<?> busquedaTexto(@RequestParam String texto) {
        return new ResponseEntity<>(busquedaService.busquedaGeneralTexto(texto), HttpStatus.OK);
    }
}
