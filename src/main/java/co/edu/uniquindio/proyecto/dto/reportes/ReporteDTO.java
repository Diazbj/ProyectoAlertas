package co.edu.uniquindio.proyecto.dto.reportes;

import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ReporteDTO(
        @NotBlank String usuario,
        @NotBlank String titulo,
        @NotBlank String categoria,
        @NotBlank String descripcion,
        UbicacionDTO ubicacion,
        @NotBlank String estadoActual,
        List<String> imagenes,
        String fechaCreacion,
        Integer cantidadImportante

) {}
