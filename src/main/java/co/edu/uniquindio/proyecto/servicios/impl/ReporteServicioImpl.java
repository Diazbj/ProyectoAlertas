package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.notificaciones.NotificacionDTO;
import co.edu.uniquindio.proyecto.dto.reportes.*;
import co.edu.uniquindio.proyecto.excepciones.DatoRepetidoException;
import co.edu.uniquindio.proyecto.excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.proyecto.servicios.impl.UsuarioServicioImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {



    private final ReporteRepo reporteRepo;
    private final ReporteMapper reporteMapper;
    private final UsuarioRepo usuarioRepo;
    private final WebSocketNotificationService webSocketNotificationService;
    private final UsuarioServicioImpl usuarioServicio;

    @Override
    public void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception {

        String id = usuarioServicio.obtenerIdSesion();

        // Mapear DTO a documento y guardar en la base de datos
        Reporte reporte = reporteMapper.toDocument(crearReporteDTO);
        reporte.setUsuarioId(new ObjectId(id));
        reporteRepo.save(reporte);
        NotificacionDTO notificacionDTO = new NotificacionDTO(
                "Nuevo Reporte",
                "Se acaba de crear un nuevo reporte: " + reporte.getTitulo(),
                "reports"
        );


        webSocketNotificationService.notificarClientes(notificacionDTO);
    }

    @Override
    public List<ReporteDTO> obtenerReportes() throws Exception {
        // Obtenemos todos los reportes
        List<Reporte> reportes = reporteRepo.findAll();

        // Convertimos los reportes a DTO
        return reportes.stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteDTO> obtenerReportesUsuario() throws Exception {

        String id = usuarioServicio.obtenerIdSesion();
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new UsuarioNoEncontradoException("No se encontró el usuario con el id "+id);
        }

        //Buscamos el usuario que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(objectId);

        //Si no se encontró el usuario, lanzamos una excepción
        if(usuarioOptional.isEmpty()){
            throw new UsuarioNoEncontradoException("No se encontró el usuario con el id "+id);
        }

        // Obtenemos los reportes asociados al usuario
        List<Reporte> reportes = reporteRepo.findByUsuarioId(objectId);

        // Convertimos los reportes a DTO usando stream y map
        return reportes.stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<ReporteDTO> obtenerReportesCerca(double latitud, double longitud) {

        double radioEnKm = 5.0;
        double radioEnRadianes = radioEnKm / 6378.1; // Convertir km a radianes

        // Lógica para encontrar reportes cercanos usando latitud y longitud
        List<Reporte> reportes = reporteRepo.findByUbicacionCerca(latitud, longitud, radioEnRadianes);
        return reportes.stream().map(reporteMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReporteDTO> obtenerTopReportes() throws Exception {
        return List.of();
    }

    @Override
    public void editarReporte(String id, EditarReporteDTO editarReporteDTO) throws Exception {

        // Validamos el id del reporte
        if (!ObjectId.isValid(id)) {
            throw new Exception("No se encontró el reporte con el id " + id);
        }

        // Convertimos el id a ObjectId
        ObjectId objectId = new ObjectId(id);
        Optional<Reporte> reporteOptional = reporteRepo.findById(id);

        // Si no se encuentra el reporte, lanzamos una excepción
        if (reporteOptional.isEmpty()) {
            throw new Exception("No se encontró el reporte con el id " + id);
        }

        // Obtener el reporte y mapear los datos actualizados
        Reporte reporte = reporteOptional.get();
        reporteMapper.toDocument(editarReporteDTO, reporte);

        // Guardamos los cambios en la base de datos
        reporteRepo.save(reporte);
    }


    @Override
    public void eliminarReporte(String id) throws Exception {

        // Validamos el id del reporte
        if (!ObjectId.isValid(id)) {
            throw new Exception("No se encontró el reporte con el id " + id);
        }

        Optional<Reporte> reporteOptional = reporteRepo.findById(id);

        // Si no se encuentra el reporte, lanzamos una excepción
        if (reporteOptional.isEmpty()) {
            throw new Exception("No se encontró el reporte con el id " + id);
        }

        //Obtenemos el reporte que se quiere eliminar
        Reporte reporte = reporteOptional.get();

        // Eliminamos el reporte
        reporteRepo.deleteById(id);


    }

    @Override
    public ReporteDTO obtenerReporte(String id) throws Exception {

        // Validamos que el id sea válido
        if (!ObjectId.isValid(id)) {
            throw new Exception("No se encontró el reporte con el id " + id);
        }

        // Buscamos el reporte que se quiere obtener usando el id en formato String
        Optional<Reporte> reporteOptional = reporteRepo.findById(id);

        // Si no se encontró el reporte, lanzamos una excepción
        if (reporteOptional.isEmpty()) {
            throw new Exception("No se encontró el reporte con el id " + id);
        }

        // Retornamos el reporte encontrado convertido a DTO
        return reporteMapper.toDTO(reporteOptional.get());
    }

    @Override
    public void agregarComentario(String id, ComentarioDTO comentarioDTO) throws Exception {

    }

    @Override
    public List<ComentarioDTO> obtenerComentarios(String idReporte) throws Exception {
        return List.of();
    }

    @Override
    public void marcarImportante(String id) throws Exception {

    }

    @Override
    public void cambiarEstado(String id, EstadoReporteDTO estadoDTO) throws Exception {

    }
}

