package com.example.ejercicioscotizacionu2.service;

import com.example.ejercicioscotizacionu2.dto.VehiculoRequest;
import com.example.ejercicioscotizacionu2.exception.CotizacionException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehiculoService {

    public Map<String, Object> calcularRenta(VehiculoRequest datos) {
        if (datos.getEdadConductor() < 18) {
            throw new CotizacionException("el conductor debe tener al menos 18 años");
        }
        if (datos.getDiasRenta() > 30) {
            throw new CotizacionException("la renta no puede superar los 30 dias");
        }
        if (datos.getKilometrosEstimados() > 5000) {
            throw new CotizacionException("los kilometros estimados no pueden superar los 5,000");
        }
        if ("camioneta".equalsIgnoreCase(datos.getTipoVehiculo()) && datos.getEdadConductor() < 25) {
            throw new CotizacionException("para rentar una camioneta el conductor debe tener al menos 25 años");
        }

        double costoDiario = switch (datos.getTipoVehiculo().toUpperCase()) {
            case "compacto" -> 550.0;
            case "sedan" -> 700.0;
            case "suv" -> 950.0;
            case "camioneta" -> 1200.0;
            default -> 0.0;
        };

        double costoRenta = costoDiario * datos.getDiasRenta();

        if (datos.getDiasRenta() >= 7) {
            costoRenta *= 0.90;
        }

        int kmIncluidos = datos.getDiasRenta() * 100;
        double cargoKmExtra = 0.0;
        if (datos.getKilometrosEstimados() > kmIncluidos) {
            cargoKmExtra = (datos.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (datos.getEdadConductor() >= 18 && datos.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmExtra) * 0.15;
        }

        double costoSeguro = 0.0;
        if (Boolean.TRUE.equals(datos.getSeguroCompleto())) {
            costoSeguro = datos.getDiasRenta() * 180.0;
        }

        double total = costoRenta + cargoKmExtra + cargoEdad + costoSeguro;

        Map<String, Object> res = new HashMap<>();
        res.put("cliente", datos.getNombreCliente());
        res.put("tipoVehiculo", datos.getTipoVehiculo());
        res.put("totalPagar", total);
        return res;
    }
}