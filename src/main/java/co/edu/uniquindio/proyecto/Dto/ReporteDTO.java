package co.edu.uniquindio.proyecto.Dto;

import java.time.LocalDateTime;

public record ReporteDTO(
        String nombre,
        String descripcion,
        LocalDateTime fechaCreacion
) {
}
