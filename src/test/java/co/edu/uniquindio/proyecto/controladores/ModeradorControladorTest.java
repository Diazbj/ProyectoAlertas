package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModeradorControladorTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String BASE_URL = "/api/moderador";

    @Test
    public void crearCategoriaTest() {
        CategoriaDTO categoriaDTO = new CategoriaDTO("test-id", "Nombre de prueba", "Descripción de prueba");

        ResponseEntity<MensajeDTO> response = testRestTemplate.postForEntity(
                BASE_URL + "/categorias",
                categoriaDTO,
                MensajeDTO.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
    }

    @Test
    public void obtenerCategoriasTest() {
        ResponseEntity<MensajeDTO> response = testRestTemplate.getForEntity(
                BASE_URL + "/categorias",
                MensajeDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
    }

    @Test
    public void editarCategoriaTest() {
        CategoriaDTO categoriaDTO = new CategoriaDTO("test-id", "Editado", "Descripción actualizada");

        HttpEntity<CategoriaDTO> request = new HttpEntity<>(categoriaDTO);
        ResponseEntity<MensajeDTO> response = testRestTemplate.exchange(
                BASE_URL + "/categorias/test-id",
                HttpMethod.PUT,
                request,
                MensajeDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
    }

    @Test
    public void eliminarCategoriaTest() {
        ResponseEntity<MensajeDTO> response = testRestTemplate.exchange(
                BASE_URL + "/categorias/test-id",
                HttpMethod.DELETE,
                null,
                MensajeDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
    }

    @Test
    public void generarInformeTest() {
        String url = BASE_URL + "/informes?fechaInicio=2024-01-01&fechaFin=2024-12-31";

        ResponseEntity<MensajeDTO> response = testRestTemplate.getForEntity(
                url,
                MensajeDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().error());
    }
}
