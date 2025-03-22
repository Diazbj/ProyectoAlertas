package co.edu.uniquindio.proyecto.dto.reportes;

import jakarta.validation.constraints.NotBlank;

public record CrearReporteDTO(
        @NotBlank String titulo,
        @NotBlank String descripcion) {
}
