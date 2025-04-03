package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.mapper.ComentarioMapper;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioMapper comentarioMapper;
    private final ComentarioRepo comentarioRepo;
    private final ReporteRepo reporteRepo;
    private final UsuarioServicioImpl usuarioServicio;


    @Override
    public void agregarComentario(String idReporte, CrearComentarioDTO crearComentarioDTO) throws Exception {
        // Verificar si el reporte existe
        if (!reporteRepo.existsById(idReporte)) {
            throw new Exception("El reporte con ID " + idReporte + " no existe.");
        }

        String idUsuario = usuarioServicio.obtenerIdSesion();
 // Extraer el nombre del usuario
        String nombreUsuario=usuarioServicio.obtenerNombreUsuario();
        // Convertir DTO a entidad Comentario
        Comentario comentario = comentarioMapper.toDocument(crearComentarioDTO);
        comentario.setId(new ObjectId()); // Generar un nuevo ID para el comentario
        comentario.setReporteId(new ObjectId(idReporte)); // Asociar el comentario al reporte
        comentario.setClienteId(new ObjectId(idUsuario)); // Asignar el ID del usuario autenticado
        comentario.setNombreUsuario(nombreUsuario);

        // Guardar comentario en MongoDB
        comentarioRepo.save(comentario);
    }



    @Override
    public List<ComentarioDTO> obtenerComentarios(String idReporte) throws Exception {
        // Verificar si el reporte existe en la base de datos
        if (!reporteRepo.existsById(idReporte)) {
            throw new Exception("El reporte con ID " + idReporte + " no existe.");
        }

        // Buscar todos los comentarios que tengan el ID del reporte
        List<Comentario> comentarios = comentarioRepo.findByReporteId(new ObjectId(idReporte));

        // Convertir la lista de Comentario a ComentarioDTO usando el mapper
        return comentarios.stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void eliminarComentario() throws Exception {

    }
}
