package co.edu.uniquindio.proyecto.dto.usuarios;

import jakarta.validation.constraints.NotBlank;

public record CambiarPasswordDTO(
        @NotBlank String actualPassword,
        @NotBlank String nuevoPassword
) {
}
