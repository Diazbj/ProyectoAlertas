package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EditarReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.EstadoReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import co.edu.uniquindio.proyecto.vo.Ubicacion;

import java.util.List;

public class ReporteServicioImpl implements ReporteServicio {
    @Override
    public void crearReporte(ReporteDTO reporteDTO) throws Exception {

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
