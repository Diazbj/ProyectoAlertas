package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EditarReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EstadoReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReporteServicioImpl implements ReporteServicio {

    @Autowired
    private final ReporteRepo reporteRepo;

    @Override
    public void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception {

        Reporte reporte = new Reporte();
        //Datos enviados por usuario
        reporte.setTitulo(crearReporteDTO.titulo());

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
        return null;
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
