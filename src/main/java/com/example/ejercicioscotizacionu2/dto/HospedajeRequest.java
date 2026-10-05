package com.example.ejercicioscotizacionu2.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HospedajeRequest {
    @NotBlank(message = "el nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "el tipo de habitación es obligatorio")
    @Pattern(regexp = "^(INDIVIDUAL|DOBLE|SUITE)$", message = "tipo de habitación no valido")
    private String tipoHabitacion;

    @NotNull(message = "el numero de noches es obligatorio")
    @Positive(message = "el numero de noches debe ser positivo")
    private Integer numeroNoches;

    @NotNull(message = "el numero de huespedes es obligatorio")
    @Positive(message = "el numero de huespedes debe ser positivo")
    private Integer numeroHuespedes;

    @NotBlank(message = "la temporada es obligatoria")
    @Pattern(regexp = "^(BAJA|REGULAR|ALTA)$", message = "temporada no valida")
    private String temporada;

    @NotNull(message = "indica si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "indica si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}
