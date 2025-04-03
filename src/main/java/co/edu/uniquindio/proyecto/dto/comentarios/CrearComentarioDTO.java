package co.edu.uniquindio.proyecto.dto.comentarios;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public record CrearComentarioDTO(
        String mensaje
) {}