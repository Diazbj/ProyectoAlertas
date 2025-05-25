package co.edu.uniquindio.proyecto.dto.moderadores;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public record CategoriaDTO(
        ObjectId id,
        @NotBlank String nombre,
        String color,
        String descripcion) {
}