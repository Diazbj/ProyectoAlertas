package co.edu.uniquindio.proyecto.dto.reportes;

import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record EditarReporteDTO(
        @NotBlank String titulo,
        @NotBlank String categoria,
        @NotBlank String descripcion,
        UbicacionDTO ubicacion,
        List<String> imagen
) {}