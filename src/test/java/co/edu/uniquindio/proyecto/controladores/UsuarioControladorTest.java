package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.config.SecurityConfig;
import co.edu.uniquindio.proyecto.dto.usuarios.*;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import co.edu.uniquindio.proyecto.servicios.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsuarioControladorTest {

    @InjectMocks
    private UsuarioControlador usuarioControlador;

    @Mock
    private UsuarioServicio usuarioServicio;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityConfig securityConfig;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crear() throws Exception {
        CrearUsuarioDTO crearUsuarioDTO = new CrearUsuarioDTO(
                "Carlos Andrés",
                "3117812222",
                "ARMENIA",
                "Carrera 10 # 20-30",
                "cccc@email.com",
                "password123"
        );

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearUsuarioDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void enviarCodigoActivacionTest() throws Exception {
        // Arrange
        String email = "diaz.jordy@hotmail.com";
        UsuarioNuevoCodigoDTO usuarioNuevoCodigoDTO = new UsuarioNuevoCodigoDTO(email);

        mockMvc.perform(post("/api/usuarios/notificacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioNuevoCodigoDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }


    @Test
    void activarCuenta() throws Exception {
       UsuarioActivacionDTO usuarioActivacionDTO = new UsuarioActivacionDTO(
                "diaz.jordy@hotmail.com",
                "7204" // Código de activación
        );

        mockMvc.perform(post("/api/usuarios/Activar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioActivacionDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void editar() throws Exception {
        String token="eyJhbGciOiJIUzM4NCJ9.eyJyb2wiOiJST0xFX0NMSUVOVEUiLCJub21icmUiOiJEaWF6IiwiZW1haWwiOiJkaWF6LmpvcmR5QGhvdG1haWwuY29tIiwic3ViIjoiNjdlYjljN2ExMDg3ZGM2NGRjOGNmYTEwIiwiaWF0IjoxNzQ0MjY2NDE0LCJleHAiOjE3NDQyNzAwMTR9.FTeadkg0bS11WkzX8YPFsOprLmSMBhPEOxYWFCFfEVgrem2jw-hTAsseVup0Ej7w";

        EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTO(
                "Jordy Diaz",
                "3221112222",
                "BOGOTA",
                "Calle nueva #123"
        );

        mockMvc.perform(put("/api/usuarios")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editarUsuarioDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void cambiarPassword() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void obtener() {
    }
}