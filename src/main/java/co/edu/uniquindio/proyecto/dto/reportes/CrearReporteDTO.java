package co.edu.uniquindio.proyecto.dto.reportes;

import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;


public record CrearReporteDTO(
        @NotBlank String titulo,
        @NotBlank String descripcion,
        @NotNull UbicacionDTO ubicacion,
        @NotBlank String ciudad,//  Aquí agregamos el objeto de ubicación
        List<String>imagenes,
        @NotBlank String categoriaId
) {}