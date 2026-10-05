package com.example.ejercicioscotizacionu2.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EnvioRequest {
    @NotBlank(message = "el codigo postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "el peso es obligatorio")
    @Positive(message = "el peso debe ser positivo")
    private Double pesokg;

    @NotNull(message = "el largo es obligatorio")
    @Positive(message = "el largo debe ser positivo")
    private Double largoCm;

    @NotNull(message = "el ancho es obligatorio")
    @Positive(message = "el ancho debe ser positivo")
    private Double anchoCm;

    @NotNull(message = "el alto es obligatorio")
    @Positive(message = "el alto debe ser positivo")
    private Double altoCm;

    @NotBlank(message = "el tipo de envío es obligatorio")
    @Pattern(regexp = "^(ESTANDAR|EXPRESS|MISMO_DIA)$", message = "El tipo de envío debe ser estandar, expres o mismo dia")
    private String tipoEnvio;

    @NotNull(message = "el valor declarado es obligatorio")
    @PositiveOrZero(message = "el valor declarado no puede ser negativo")
    private Double valorDeclarado;
}