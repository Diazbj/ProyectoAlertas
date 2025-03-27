package co.edu.uniquindio.proyecto.dto.login;

import co.edu.uniquindio.proyecto.modelo.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @Email @NotBlank String email,
        @NotBlank String password,
        @NotNull Rol rol
) {}
