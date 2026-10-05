package com.example.ejercicioscotizacionu2.controller;

import com.example.ejercicioscotizacionu2.dto.EnvioRequest;
import com.example.ejercicioscotizacionu2.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/envios")
@CrossOrigin(origins = "*")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Object>> cotizar(@Valid @RequestBody EnvioRequest datos) {
        return ResponseEntity.ok(envioService.calcularCotizacion(datos));
    }
}