package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.*;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReporteServicio {

    void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception ;

    List<ReporteDTO> obtenerReportes() throws Exception;

    List<ReporteDTO> obtenerReportesUsuario() throws Exception ;

    List<ReporteDTO> obtenerReportesCerca(double latitud, double longitud) throws Exception ;

    List<ReporteDTO> obtenerTopReportes() throws Exception ;

    void editarReporte(String id, EditarReporteDTO reporteDTO) throws Exception ;

    void eliminarReporte( String id) throws Exception ;

    ReporteDTO obtenerReporte(String id) throws Exception;

    int marcarImportante( String id) throws Exception ;

    void cambiarEstado(String id, EstadoReporteDTO estadoDTO) throws Exception ;

}
