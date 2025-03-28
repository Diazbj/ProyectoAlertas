package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.login.LoginRequestDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordNuevoDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordOlvidadoDTO;
import co.edu.uniquindio.proyecto.servicios.LoginServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uniquindio.proyecto.seguridad.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginControlador {

    private final JwtUtil jwtUtil;
    private final LoginServicio loginServicio;

    @PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest) throws Exception {
        String token = loginServicio.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validarToken(@RequestHeader("Authorization") String authorizationHeader) throws Exception {
        String token = authorizationHeader.replace("Bearer ", "");
        return ResponseEntity.ok().body(loginServicio.validarToken(token));
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
