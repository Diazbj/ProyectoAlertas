package co.edu.uniquindio.proyecto.dto.reportes;

import java.time.LocalDateTime;

public record ReporteDTO(
        String nombre,
        String descripcion,
        LocalDateTime fechaCreacion
) {
}
