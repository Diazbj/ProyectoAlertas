package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.proyecto.mapper.CategoriaMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepo categoriaRepo;

    @Override
    public void crearCategoria(CategoriaDTO categoriaDTO) throws Exception {
        Categoria categoria = categoriaMapper.toDocument(categoriaDTO);

        categoriaRepo.save(categoria);

    }

    @Override
    public List<CategoriaDTO> obtenerCategorias() throws Exception {
        return null;
    }

    @Override
    public void editarCategoria(String id, CategoriaDTO categoriaDTO) throws Exception {

    }

    @Override
    public void eliminarCategoria(String id) throws Exception {

    }

    @Override
    public List<ReporteDTO> generarInforme(String fechaInicio, String fechaFin, String categoria, Double latitud, Double longitud, int radio) throws Exception {
        return List.of();
    }
}
