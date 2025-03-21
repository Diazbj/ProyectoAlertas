package co.edu.uniquindio.proyecto.modelo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class CodigoValidacion {

    private LocalDateTime fecha;
    private String codigo;

}
