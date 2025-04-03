package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.login.LoginDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordNuevoDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordOlvidadoDTO;
import co.edu.uniquindio.proyecto.seguridad.JWTUtils;
import co.edu.uniquindio.proyecto.servicios.LoginServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginControlador {

    private final JWTUtils jwtUtils;
    private final LoginServicio loginServicio;

    @PostMapping
    @Operation(summary = "Login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = loginServicio.login(loginDTO);
        String token = tokenDTO.toString();
        return ResponseEntity.ok(token);
    }


    @PostMapping("/codigoValidacion")
    public ResponseEntity<MensajeDTO<String>> recuperarPassword(@Valid @RequestBody PasswordOlvidadoDTO passwordOlvidadoDTO) throws Exception {
        loginServicio.recuperarPassword(passwordOlvidadoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Código enviado al correo"));
    }

    @PostMapping("/password/nuevo")
    public ResponseEntity<MensajeDTO<String>> actualizarPassword(@Valid @RequestBody PasswordNuevoDTO passwordNuevoDTO) throws Exception {
        loginServicio.actualizarPassword(passwordNuevoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contraseña actualizada"));
    }


}
