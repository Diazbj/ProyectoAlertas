package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {

    @Id
    private String id;

    @DBRef
    private Usuario usuario;

    private String titulo;
    private String categoria;
    private String descripcion;
    private Ubicacion ubicacion;
    private String estadoActual;
    private LocalDateTime fechaCreacion;
    private List<String> imagenes;
    private int importancia;
}