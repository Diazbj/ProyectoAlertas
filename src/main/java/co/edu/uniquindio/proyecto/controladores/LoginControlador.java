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
@RequestMapping("/login")
public class LoginControlador {

    private final JwtUtil jwtUtil;
    private final LoginServicio loginServicio;

    @PostMapping
    public String login(@Valid @RequestBody LoginRequestDTO loginRequest) throws Exception {
        if (loginRequest.email().equals(loginRequest.email()) && loginRequest.password().equals(loginRequest.password())) {
            return jwtUtil.generarToken(loginRequest.email(),loginRequest.rol());
        } else {
            throw new Exception("Credenciales incorrectas");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validarToken(@RequestHeader("Authorization") String authorizationHeader) throws Exception {
        String token = authorizationHeader.replace("Bearer ", "");
        return ResponseEntity.ok().body(loginServicio.validarToken(token));
    }


    @PostMapping("/codigoValidacion")
    public ResponseEntity<MensajeDTO<String>> recuperarPassword(@Valid @RequestBody PasswordOlvidadoDTO passwordOlvidadoDTO) throws Exception {
        return ResponseEntity.ok(new MensajeDTO<>(false, "Código enviado al correo"));
    }

    @PostMapping("/password/nuevo")
    public ResponseEntity<MensajeDTO<String>> actualizarPassword(@Valid @RequestBody PasswordNuevoDTO passwordNuevoDTO) throws Exception {
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contraseña actualizada"));
    }


}
