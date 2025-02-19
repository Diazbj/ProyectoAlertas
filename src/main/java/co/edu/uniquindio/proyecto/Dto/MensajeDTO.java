package co.edu.uniquindio.proyecto.Dto;

public record MensajeDTO<T>(
        boolean error,
        T mensaje
) {
}
