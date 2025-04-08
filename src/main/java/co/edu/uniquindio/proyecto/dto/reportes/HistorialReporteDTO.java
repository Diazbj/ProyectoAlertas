package co.edu.uniquindio.proyecto.dto.reportes;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;

import java.time.LocalDateTime;

public record HistorialReporteDTO(
        String motivo,
        EstadoReporte estado,
        String fecha,
        String fechaLimiteEdicion
        )
{}
