package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.login.LoginRequestDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordNuevoDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordOlvidadoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface LoginServicio {

    String login( LoginRequestDTO loginRequest) throws Exception ;

    public ResponseEntity<?> validarToken(@RequestHeader("Authorization") String token) throws Exception;

    void  recuperarPassword(PasswordOlvidadoDTO passwordOlvidadoDTO) throws Exception ;

    void  actualizarPassword(PasswordNuevoDTO passwordNuevoDTO) throws Exception;
}
