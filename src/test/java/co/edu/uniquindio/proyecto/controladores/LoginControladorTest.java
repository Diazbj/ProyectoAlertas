package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.login.LoginDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordNuevoDTO;
import co.edu.uniquindio.proyecto.dto.login.PasswordOlvidadoDTO;
import co.edu.uniquindio.proyecto.seguridad.JWTUtils;
import co.edu.uniquindio.proyecto.servicios.LoginServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(LoginControlador.class)
class LoginControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoginServicio loginServicio;

    @Mock
    private JWTUtils jwtUtils;

    @InjectMocks
    private LoginControlador loginControlador;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO("correo@prueba.com", "password123");
        TokenDTO tokenDTO = new TokenDTO("abc.def.ghi");

        when(loginServicio.login(loginDTO)).thenReturn(tokenDTO);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void recuperarPasswordTest() throws Exception {
        PasswordOlvidadoDTO dto = new PasswordOlvidadoDTO("correo@prueba.com");

        doNothing().when(loginServicio).recuperarPassword(dto);

        mockMvc.perform(post("/api/login/recuperarPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarPasswordTest() throws Exception {
        PasswordNuevoDTO dto = new PasswordNuevoDTO("correo@prueba.com", "nuevoPassword", "codigo123");

        doNothing().when(loginServicio).actualizarPassword(dto);

        mockMvc.perform(post("/api/login/password/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}