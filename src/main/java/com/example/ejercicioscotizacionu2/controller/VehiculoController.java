package com.example.ejercicioscotizacionu2.controller;

import com.example.ejercicioscotizacionu2.dto.VehiculoRequest;
import com.example.ejercicioscotizacionu2.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Object>> cotizar(@Valid @RequestBody VehiculoRequest datos) {
        return ResponseEntity.ok(vehiculoService.calcularRenta(datos));
    }
}
