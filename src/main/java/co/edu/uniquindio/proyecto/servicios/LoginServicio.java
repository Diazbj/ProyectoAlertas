package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.login.LoginDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordNuevoDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordOlvidadoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface LoginServicio {

    TokenDTO login(LoginDTO loginDTO) throws Exception ;


    void  recuperarPassword(PasswordOlvidadoDTO passwordOlvidadoDTO) throws Exception ;

    void  actualizarPassword(PasswordNuevoDTO passwordNuevoDTO) throws Exception;
}
