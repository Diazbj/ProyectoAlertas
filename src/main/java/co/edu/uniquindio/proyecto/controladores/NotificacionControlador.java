package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.notificaciones.NotificacionDTO;
import co.edu.uniquindio.proyecto.dto.notificaciones.NotificacionUbicacionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificaciones")
public class NotificacionControlador {

    @PostMapping("/email")
    public ResponseEntity<MensajeDTO<String>> enviarNotificacion(@Valid @RequestBody NotificacionDTO notificacionDTO) {
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Notificación enviada"));
    }

    @PostMapping("/ubicacion")
    public ResponseEntity<MensajeDTO<String>> enviarNotificacionUbicacion(@Valid @RequestBody NotificacionUbicacionDTO notificacionUbicacionDTO) {
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Notificación enviada"));
    }
}