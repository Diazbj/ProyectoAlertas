package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EditarReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EstadoReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.excepciones.DatoRepetidoException;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {



    private final ReporteRepo reporteRepo;
    private final ReporteMapper reporteMapper;

    @Override
    public void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception {

        // Validar si ya existe un reporte con la misma ubicación y descripción
        if (existeReporte(crearReporteDTO.ubicacion().latitud(), crearReporteDTO.ubicacion().longitud(), crearReporteDTO.descripcion())) {
            throw new DatoRepetidoException("Ya existe un reporte similar en la misma ubicación.");
        }

        // Mapear DTO a documento y guardar en la base de datos
        Reporte reporte = reporteMapper.toDocument(crearReporteDTO);
        reporteRepo.save(reporte);
    }

    private boolean existeReporte(double latitud, double longitud, String descripcion) {
        return reporteRepo.findByUbicacion_LatitudAndUbicacion_LongitudAndDescripcion(latitud, longitud, descripcion).isPresent();

    }

    @Override
    public List<ReporteDTO> obtenerReportes() throws Exception {
        return List.of();
    }

    @Override
    public List<ReporteDTO> obtenerReportesUsuario(String idUsuario) throws Exception {
        return List.of();
    }

    @Override
    public List<ReporteDTO> obtenerReportesCerca(Ubicacion ubicacion) throws Exception {
        return List.of();
    }

    @Override
    public List<ReporteDTO> obtenerTopReportes() throws Exception {
        return List.of();
    }

    @Override
    public void editarReporte(String id, EditarReporteDTO reporteDTO) throws Exception {

    }

    @Override
    public void eliminarReporte(String id) throws Exception {

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

