package com.example.ejercicioscotizacionu2.service;

import com.example.ejercicioscotizacionu2.dto.EnvioRequest;
import com.example.ejercicioscotizacionu2.exception.CotizacionException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EnvioService {

    public Map<String, Object> calcularCotizacion(EnvioRequest datos) {
        double volumen = datos.getLargoCm() * datos.getAnchoCm() * datos.getAltoCm();

        if (datos.getPesokg() > 50) {
            throw new CotizacionException("no se aceptan paquetes con peso mayor a 50 kg");
        }
        if (datos.getLargoCm() > 150 || datos.getAnchoCm() > 150 || datos.getAltoCm() > 150) {
            throw new CotizacionException("no se aceptan paquetes con alguna dimension superior a 150 cm");
        }
        if (volumen > 1000000) {
            throw new CotizacionException("no se aceptan paquetes con un volumen superior a 1,000,000 cm");
        }

        double costo = 80.0;
        costo += datos.getPesokg() * 12.0;

        if (volumen > 50000) {
            costo += 100.0;
        }

        if ("EXPRESS".equalsIgnoreCase(datos.getTipoEnvio())) {
            costo *= 1.40;
        } else if ("MISMO_DIA".equalsIgnoreCase(datos.getTipoEnvio())) {
            costo *= 1.70;
        }

        if (datos.getValorDeclarado() > 10000) {
            costo += datos.getValorDeclarado() * 0.02;
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("codigoPostal", datos.getCodigoPostal());
        respuesta.put("tipoEnvio", datos.getTipoEnvio());
        respuesta.put("costoTotal", costo);
        return respuesta;
    }
}
