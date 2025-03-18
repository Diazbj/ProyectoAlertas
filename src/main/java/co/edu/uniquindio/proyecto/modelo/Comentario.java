package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.modelo.Reporte;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    private String id;

    private Usuario usuario;

    private Reporte reporte;

    private String comentario;
    private LocalDateTime fechaCreacion;
}