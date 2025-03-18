package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.modelo.Reporte;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "estado_reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoReporte {

    @Id
    private String id;

    private Reporte reporte;

    private Usuario usuario;

    private String nuevoEstado;
    private String motivo;
    private LocalDateTime fechaCambio;
}