package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;

import co.edu.uniquindio.proyecto.dto.moderadores.InformeDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/moderador")
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;


    @PostMapping("/categorias")
    @Operation(summary = "Crear categoria")
    public ResponseEntity<MensajeDTO<String>> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception {
        moderadorServicio.crearCategoria(categoriaDTO);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Categoría creada exitosamente"));
    }


    @GetMapping("/categorias")
    @Operation(summary = "Obtener todas las categorias")
    public ResponseEntity<MensajeDTO<String>> obtenerCategorias() throws Exception {
        moderadorServicio.obtenerCategorias();
        return ResponseEntity.ok(new MensajeDTO<>(false, "categorias"));
    }

    @PutMapping("/categorias/{id}")
    @Operation(summary = "Editar categoria")
    public ResponseEntity<MensajeDTO<String>> editarCategoria(@PathVariable String id, @Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception {

        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoría editada exitosamente"));
    }

    @DeleteMapping("/categorias/{id}")
    @Operation(summary = "Eliminar categoria")
    public ResponseEntity<MensajeDTO<String>> eliminarCategoria(@PathVariable String id) throws Exception {
        moderadorServicio.eliminarCategoria(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoría eliminada"));
    }

    @GetMapping("/informes")
    @Operation(summary = "Crear informe de reportes")
    public ResponseEntity<MensajeDTO<List<InformeDTO>>> generarInforme(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String ciudad) throws Exception {


        List<InformeDTO> informes = moderadorServicio.generarInforme(ciudad, categoria, fechaInicio, fechaFin);

        return ResponseEntity.ok(new MensajeDTO<>(false, informes));
    }
}