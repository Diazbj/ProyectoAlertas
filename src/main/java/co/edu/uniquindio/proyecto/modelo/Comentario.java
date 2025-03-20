package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comentarios")
@AllArgsConstructor
public class Comentario {


    private ObjectId id;
    private ObjectId clienteId ;
    private ObjectId reporteId;
    private String mensaje;
    private LocalDateTime fechaCreacion;
}