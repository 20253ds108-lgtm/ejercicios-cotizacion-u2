package com.example.ejercicioscotizacionu2.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VehiculoRequest {
    @NotBlank(message = "el nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "la edad es obligatoria")
    private Integer edadConductor;

    @NotBlank(message = "el tipo de vehículo es obligatorio")
    @Pattern(regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$", message = "el tipo de vehiculo no es valido")
    private String tipoVehiculo;

    @NotNull(message = "los dias de renta son obligatorios")
    @Positive(message = "los dias de renta deben ser mayores a 0")
    private Integer diasRenta;

    @NotNull(message = "los kilometros estimados son obligatorios")
    @PositiveOrZero(message = "los kilometros estimados no pueden ser negativos")
    private Integer kilometrosEstimados;

    @NotNull(message = "debe indicar si incluye seguro completo")
    private Boolean seguroCompleto;
}
