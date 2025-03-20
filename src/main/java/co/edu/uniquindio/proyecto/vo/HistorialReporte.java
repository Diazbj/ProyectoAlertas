package co.edu.uniquindio.proyecto.vo;


import co.edu.uniquindio.proyecto.enums.EstadoReporte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class HistorialReporte {
    private String motivo;
    private EstadoReporte estado;
    private LocalDateTime fecha;
    private ObjectId clienteId;

}
