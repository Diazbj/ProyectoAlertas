package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.InformeDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ModeradorServicio {

    void crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception;


    @GetMapping("/categorias")
    public ResponseEntity<MensajeDTO<String>> obtenerCategorias() throws Exception;

    void editarCategoria( String id, CategoriaDTO categoriaDTO) throws Exception;


    void eliminarCategoria(String id) throws Exception;

    List<InformeDTO> generarInforme(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double latitud,
            @RequestParam(required = false) Double longitud,
            @RequestParam(required = false, defaultValue = "5") int radio)throws Exception;

}
