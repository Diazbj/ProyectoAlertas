package co.edu.uniquindio.proyecto.dto.reportes;

public record EditarReporteDTO(
        String titulo,
        String categoria,
        String descripcion,
        //Pregunta
        String ubicacion,
        String imagen) {
}
