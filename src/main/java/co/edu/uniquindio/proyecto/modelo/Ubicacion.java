package co.edu.uniquindio.proyecto.modelo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {
    private double latitud;
    private double longitud;
}