package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    private ObjectId id;
    private String email;
    private String asunto;
    private String mensaje;
    private LocalDateTime fecha;
    private String tipo;
    private ObjectId idUsuario;
    private ObjectId reporteId;
    private boolean leida;

}
