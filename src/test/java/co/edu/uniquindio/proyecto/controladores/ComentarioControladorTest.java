package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ComentarioControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ComentarioServicio comentarioServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ComentarioControlador comentarioControlador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void agregarComentarioTest() throws Exception {
        String idReporte = "123abc";
        CrearComentarioDTO comentarioDTO = new CrearComentarioDTO("Este es un comentario de prueba");

        mockMvc.perform(post("/api/comentario/{idReporte}", idReporte)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comentarioDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerComentariosTest() throws Exception {
        String idReporte = "123abc";

        ComentarioDTO comentario1 = new ComentarioDTO("Juan", "Buen reporte", "2024-10-11T10:00:00");
        ComentarioDTO comentario2 = new ComentarioDTO("Maria", "Gracias por la información", "2024-10-11T12:30:00");

        when(comentarioServicio.obtenerComentarios(idReporte)).thenReturn(List.of(comentario1, comentario2));

        mockMvc.perform(get("/api/comentario/{idReporte}", idReporte))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.mensaje").isArray())
                .andExpect(jsonPath("$.mensaje[0].nombreUsuario").value("Juan"))
                .andExpect(jsonPath("$.mensaje[1].nombreUsuario").value("Maria"));
    }
}