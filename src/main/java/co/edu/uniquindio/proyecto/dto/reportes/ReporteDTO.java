package co.edu.uniquindio.proyecto.dto.reportes;

import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ReporteDTO(
        @NotBlank String nombreUsuario,
        @NotBlank String titulo,
        @NotBlank String categoria,
        @NotBlank String descripcion,
        UbicacionDTO ubicacion,
        @NotBlank String estadoActual,
        List<String> imagenes,
        String fechaCreacion
) {}
