package com.example.ejercicioscotizacionu2.service;

import com.example.ejercicioscotizacionu2.dto.HospedajeRequest;
import com.example.ejercicioscotizacionu2.exception.CotizacionException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HospedajeService {

    public Map<String, Object> calcularHospedaje(HospedajeRequest datos) {
        if (datos.getNumeroNoches() > 30) {
            throw new CotizacionException("el numero de noches no puede superar 30");
        }

        String hab = datos.getTipoHabitacion().toUpperCase();
        int maxCapacidad = switch (hab) {
            case "individual" -> 1;
            case "doble" -> 2;
            case "suite" -> 4;
            default -> 0;
        };

        if (datos.getNumeroHuespedes() > maxCapacidad) {
            throw new CotizacionException("el numero de huespedes supera la capacidad maxima de la habitacion " + hab);
        }

        double costoNoche = switch (hab) {
            case "individual" -> 700.0;
            case "doble" -> 1100.0;
            case "suite" -> 1800.0;
            default -> 0.0;
        };

        double costoHospedaje = costoNoche * datos.getNumeroNoches();

        switch (datos.getTemporada().toUpperCase()) {
            case "baja" -> costoHospedaje *= 0.90;
            case "alta" -> costoHospedaje *= 1.25;
            default -> {}
        }

        if (datos.getNumeroNoches() >= 7) {
            costoHospedaje *= 0.92;
        }

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(datos.getIncluyeDesayuno())) {
            costoDesayuno = datos.getNumeroHuespedes() * datos.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(datos.getIncluyeEstacionamiento())) {
            costoEstacionamiento = datos.getNumeroNoches() * 100.0;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        Map<String, Object> res = new HashMap<>();
        res.put("huesped", datos.getNombreHuesped());
        res.put("subtotal", subtotal);
        res.put("impuesto", impuesto);
        res.put("totalPagar", total);
        return res;
    }
}
