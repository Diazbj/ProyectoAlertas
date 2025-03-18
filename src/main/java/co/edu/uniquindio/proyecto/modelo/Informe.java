package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "informes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Informe {

    @Id
    private String id;

    private String categoria;
    private int cantidadReportes;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double latitud;
    private Double longitud;
    private int radio;
}