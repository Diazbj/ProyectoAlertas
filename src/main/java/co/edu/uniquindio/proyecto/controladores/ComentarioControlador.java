package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/comentario")
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/{idReporte}")
    public ResponseEntity<String> agregarComentario(
            @PathVariable String idReporte,
            @RequestBody CrearComentarioDTO crearComentarioDTO) {
        try {
            comentarioServicio.agregarComentario(idReporte, crearComentarioDTO);
            return ResponseEntity.ok("Comentario agregado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<List<ComentarioDTO>>> obtenerComentarios(@PathVariable String idReporte) throws Exception {
        List<ComentarioDTO> comentarios=comentarioServicio.obtenerComentarios(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, comentarios));
    }

}
