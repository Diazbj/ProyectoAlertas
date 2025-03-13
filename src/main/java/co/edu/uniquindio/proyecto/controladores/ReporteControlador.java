package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.reportes.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EditarReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteControlador{

    @PostMapping
    public void crearReporte(@RequestBody CrearReporteDTO reporte) throws Exception {
    }

    @PutMapping("/{id}")
    public void editarReporte(@PathVariable String id, @RequestBody EditarReporteDTO reporte) throws Exception {
    }

    @DeleteMapping("/{id}/eliminar")
    public void eliminarReporte(@PathVariable String id) throws Exception {
    }

    @GetMapping("/{id}")
    public ReporteDTO obtenerReporte(@PathVariable String id) throws Exception {
        return null;
    }

    @GetMapping
    public List<ReporteDTO> listarReportes() {
        return null;
    }
}
