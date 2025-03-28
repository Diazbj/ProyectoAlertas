package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.*;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/reportes")
public class ReporteControlador{

    private final ReporteServicio reporteServicio; // Inyectar servicio

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearReporte(@Valid @RequestBody CrearReporteDTO crearReporteDTO) throws Exception {
        reporteServicio.crearReporte(crearReporteDTO);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Reporte creado exitosamente"));
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<String>> obtenerReportes() throws Exception{
        reporteServicio.obtenerReportes();
        return ResponseEntity.ok(new MensajeDTO<>(false, "reportes"));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> obtenerReportesUsuario(@PathVariable String idUsuario) throws Exception {
        List<ReporteDTO> reportes = reporteServicio.obtenerReportesUsuario(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, reportes));
    }

    @GetMapping("/ubicacion")
    public ResponseEntity<MensajeDTO<String>> obtenerReportesCerca(@Valid @RequestBody ReporteUbicacionDTO reporteUbicacionDTO ) throws Exception {
        reporteServicio.obtenerReportesCerca(reporteUbicacionDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "reportes"));
    }

    @GetMapping("/topImportantes")
    public ResponseEntity<MensajeDTO<String>> obtenerTopReportes() throws Exception {
        reporteServicio.obtenerTopReportes();
        return ResponseEntity.ok(new MensajeDTO<>(false, "reportes"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> editarReporte(@PathVariable String id, @Valid @RequestBody EditarReporteDTO reporteDTO) throws Exception {
        reporteServicio.editarReporte(id, reporteDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte actualizado correctamente"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarReporte(@PathVariable String id) throws Exception {
        reporteServicio.eliminarReporte(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<ReporteDTO>> obtenerReporte(@PathVariable String id) throws Exception {
        ReporteDTO reporte = reporteServicio.obtenerReporte(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, reporte));
    }

    @PostMapping("/{id}/comentario")
    public ResponseEntity<MensajeDTO<String>> agregarComentario(
            @PathVariable String id,
            @Valid @RequestBody ComentarioDTO comentarioDTO) throws Exception {
        reporteServicio.agregarComentario(id, comentarioDTO);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Comentario creado exitosamente"));
    }

    @GetMapping("/{idReporte}/comentarios")
    public ResponseEntity<MensajeDTO<String>> obtenerComentarios(@PathVariable String idReporte) throws Exception {
        reporteServicio.obtenerComentarios(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, "comentarios"));
    }

    @PutMapping("/{id}/importante")
    public ResponseEntity<MensajeDTO<String>> marcarImportante(@PathVariable String id) throws Exception {
        reporteServicio.marcarImportante(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte marcado como importante"));
    }

    @PostMapping("/{id}/estado")
    public ResponseEntity<MensajeDTO<String>> cambiarEstado(
            @PathVariable String id,
            @Valid @RequestBody EstadoReporteDTO estadoDTO) throws Exception {
        reporteServicio.cambiarEstado(id, estadoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Estado del reporte actualizado"));
    }

}
