package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.login.LoginRequestDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordNuevoDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordOlvidadoDTO;
import co.edu.uniquindio.proyecto.excepciones.DatosInvalidosException;
import co.edu.uniquindio.proyecto.excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.seguridad.JwtUtil;
import co.edu.uniquindio.proyecto.servicios.LoginServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginServicioImpl implements LoginServicio {

    private final UsuarioRepo usuarioRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public String login(LoginRequestDTO loginRequest) throws Exception {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByEmail(loginRequest.email());

        if (usuarioOpt.isEmpty()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado.");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(loginRequest.password(), usuario.getPassword())) {
            throw new DatosInvalidosException("Contraseña incorrecta.");
        }

        return jwtUtil.generarToken(usuario.getNombre(),usuario.getRol());
    }

    @Override
    public ResponseEntity<?> validarToken(String token) throws Exception {
        try {
            // Eliminar "Bearer " si el token lo incluye
            token = token.replace("Bearer ", "").trim();

            // Validar si el token es correcto
            boolean esValido = jwtUtil.validarToken(token);

            if (esValido) {
                String usuario = jwtUtil.obtenerUsuario(token);
                return ResponseEntity.ok("Token válido para el usuario: " + usuario);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la validación del token: " + e.getMessage());
        }
    }

    @Override
    public void recuperarPassword(PasswordOlvidadoDTO passwordOlvidadoDTO) throws Exception {

    }

    @Override
    public void actualizarPassword(PasswordNuevoDTO passwordNuevoDTO) throws Exception {

    }
}
