package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.InformeDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moderador")
public class ModeradorControlador {


    @PostMapping("/categorias")
    public ResponseEntity<MensajeDTO<String>> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Categoría creada exitosamente"));
    }


    @GetMapping("/categorias")
    public ResponseEntity<MensajeDTO<String>> obtenerCategorias() {
        return ResponseEntity.ok(new MensajeDTO<>(false, "categorias"));
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<MensajeDTO<String>> editarCategoria(@PathVariable String id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoría editada exitosamente"));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCategoria(@PathVariable String id) {
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoría eliminada"));
    }

    @GetMapping("/informes")
    public ResponseEntity<MensajeDTO<List<InformeDTO>>> generarInforme(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double latitud,
            @RequestParam(required = false) Double longitud,
            @RequestParam(required = false, defaultValue = "5") int radio) {

        List<InformeDTO> informes = null;
        return ResponseEntity.ok(new MensajeDTO<>(false, informes));
    }
}