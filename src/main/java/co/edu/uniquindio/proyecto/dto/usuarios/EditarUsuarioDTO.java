package co.edu.uniquindio.proyecto.dto.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record EditarUsuarioDTO (
        @NotBlank @Length(max = 100) String nombre,
       @Length(max = 10) String telefono,
        @NotBlank @Length(max = 100) String ciudad,
        @NotBlank @Length(max = 100) String direccion
) {
}