package com.example.ejercicioscotizacionu2.controller;

import com.example.ejercicioscotizacionu2.dto.HospedajeRequest;
import com.example.ejercicioscotizacionu2.service.HospedajeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hospedaje")
@CrossOrigin(origins = "*")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Object>> cotizar(@Valid @RequestBody HospedajeRequest datos) {
        return ResponseEntity.ok(hospedajeService.calcularHospedaje(datos));
    }
}
