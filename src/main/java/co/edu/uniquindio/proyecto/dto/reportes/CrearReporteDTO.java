package co.edu.uniquindio.proyecto.dto.reportes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;


public record CrearReporteDTO(
        @NotBlank String titulo,
        @NotBlank String descripcion,
        @NotNull UbicacionDTO ubicacion, //  Aquí agregamos el objeto de ubicación
        List<String>imagenes
) {}