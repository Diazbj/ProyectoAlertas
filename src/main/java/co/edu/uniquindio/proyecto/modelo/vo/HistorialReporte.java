package co.edu.uniquindio.proyecto.modelo.vo;


import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class HistorialReporte {
    @Id
    private ObjectId id;
    private ObjectId reporteId;
    private String motivo;
    private EstadoReporte estado;
    private LocalDateTime fecha;
    private LocalDateTime fechaLimiteEdicion;


}
