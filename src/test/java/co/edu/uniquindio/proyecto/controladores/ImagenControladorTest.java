package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.servicios.ImagenServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(controllers = ImagenControlador.class)
class ImagenControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ImagenServicio imagenServicio;

    @InjectMocks
    private ImagenControlador imagenControlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void subirImagenTest() throws Exception {
        // Simulamos la imagen que se va a enviar en la petición
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "ejemplo.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "contenido de prueba".getBytes()
        );

        // Simulamos la respuesta del servicio
        Map<String, Object> respuestaMock = new HashMap<>();
        respuestaMock.put("url", "http://imagen.com/ejemplo.jpg");

        // Configuramos el mock
        when(imagenServicio.subirImagen(imagen)).thenReturn(respuestaMock);

        // Ejecutamos el request
        mockMvc.perform(multipart("/api/imagenes")
                        .file(imagen)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}