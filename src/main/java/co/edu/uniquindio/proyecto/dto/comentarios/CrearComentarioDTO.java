package co.edu.uniquindio.proyecto.dto.comentarios;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public record CrearComentarioDTO(
        ObjectId id,
        String mensaje,  // Coincide con "mensaje" en Comentario
        String nombre,
        LocalDateTime fecha
) {}