package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import co.edu.uniquindio.proyecto.dto.notificaciones.NotificacionDTO;
import co.edu.uniquindio.proyecto.dto.notificaciones.NotificacionUbicacionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificacionControladorTest {

    private NotificacionControlador notificacionControlador;

    @BeforeEach
    void setUp() {
        notificacionControlador = new NotificacionControlador();
    }

    @Test
    void enviarNotificacionTest() throws Exception {
        // Arrange
        NotificacionDTO notificacionDTO = new NotificacionDTO(
                "test@correo.com",
                "Título de prueba",
                "Cuerpo de la notificación"
        );

        // Act
        ResponseEntity<MensajeDTO<String>> response = notificacionControlador.enviarNotificacion(notificacionDTO);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
        assertEquals("Notificación enviada", response.getBody().mensaje());
    }

    @Test
    void enviarNotificacionUbicacionTest() throws Exception {
        // Arrange
        NotificacionUbicacionDTO notificacionUbicacionDTO = new NotificacionUbicacionDTO(
                "reporte123",
                new UbicacionDTO(4.621, -74.072),
                10
        );

        // Act
        ResponseEntity<MensajeDTO<String>> response = notificacionControlador.enviarNotificacionUbicacion(notificacionUbicacionDTO);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
        assertEquals("Notificación enviada", response.getBody().mensaje());
    }
}