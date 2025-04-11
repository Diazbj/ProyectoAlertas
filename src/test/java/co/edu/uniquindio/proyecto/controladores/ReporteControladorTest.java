package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.reportes.*;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReporteControladorTest {

    @Mock
    private ReporteServicio reporteServicio;

    @InjectMocks
    private ReporteControlador reporteControlador;

    public ReporteControladorTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearReporte() throws Exception {
        CrearReporteDTO dto = mock(CrearReporteDTO.class);
        ResponseEntity<MensajeDTO<String>> response = reporteControlador.crearReporte(dto);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Reporte creado exitosamente", response.getBody().mensaje());
        verify(reporteServicio).crearReporte(dto);
    }

    @Test
    public void testObtenerReportes() throws Exception {
        when(reporteServicio.obtenerReportes()).thenReturn(Collections.emptyList());
        ResponseEntity<MensajeDTO<List<ReporteDTO>>> response = reporteControlador.obtenerReportes();
        assertFalse(response.getBody().error());
        verify(reporteServicio).obtenerReportes();
    }

    @Test
    public void testObtenerReportesUsuario() throws Exception {
        when(reporteServicio.obtenerReportesUsuario()).thenReturn(Collections.emptyList());
        ResponseEntity<MensajeDTO<List<ReporteDTO>>> response = reporteControlador.obtenerReportesUsuario();
        assertFalse(response.getBody().error());
        verify(reporteServicio).obtenerReportesUsuario();
    }

    @Test
    public void testObtenerReportesCerca() throws Exception {
        when(reporteServicio.obtenerReportesCerca(1.0, 2.0)).thenReturn(Collections.emptyList());
        ResponseEntity<MensajeDTO<List<ReporteDTO>>> response = reporteControlador.obtenerReportesCerca(1.0, 2.0);
        assertFalse(response.getBody().error());
        verify(reporteServicio).obtenerReportesCerca(1.0, 2.0);
    }

    @Test
    public void testObtenerTopReportes() throws Exception {
        when(reporteServicio.obtenerTopReportes()).thenReturn(Collections.emptyList());
        ResponseEntity<MensajeDTO<List<ReporteDTO>>> response = reporteControlador.obtenerTopReportes();
        assertFalse(response.getBody().error());
        verify(reporteServicio).obtenerTopReportes();
    }

    @Test
    public void testEditarReporte() throws Exception {
        EditarReporteDTO dto = mock(EditarReporteDTO.class);
        ResponseEntity<MensajeDTO<String>> response = reporteControlador.editarReporte("123", dto);
        assertEquals("Reporte actualizado correctamente", response.getBody().mensaje());
        verify(reporteServicio).editarReporte("123", dto);
    }

    @Test
    public void testEliminarReporte() throws Exception {
        ResponseEntity<MensajeDTO<String>> response = reporteControlador.eliminarReporte("123");
        assertEquals("Reporte eliminado", response.getBody().mensaje());
        verify(reporteServicio).eliminarReporte("123");
    }

    @Test
    public void testObtenerReporte() throws Exception {
        ReporteDTO mockReporte = mock(ReporteDTO.class);
        when(reporteServicio.obtenerReporte("123")).thenReturn(mockReporte);
        ResponseEntity<MensajeDTO<ReporteDTO>> response = reporteControlador.obtenerReporte("123");
        assertEquals(mockReporte, response.getBody().mensaje());
        verify(reporteServicio).obtenerReporte("123");
    }

    @Test
    public void testMarcarImportante() throws Exception {
        when(reporteServicio.marcarImportante("123")).thenReturn(5);
        ResponseEntity<MensajeDTO<Integer>> response = reporteControlador.marcarImportante("123");
        assertEquals(5, response.getBody().mensaje());
        verify(reporteServicio).marcarImportante("123");
    }

    @Test
    public void testCambiarEstado() throws Exception {
        EstadoReporteDTO estadoDTO = mock(EstadoReporteDTO.class);
        ResponseEntity<MensajeDTO<String>> response = reporteControlador.cambiarEstado("123", estadoDTO);
        assertEquals("Estado del reporte actualizado", response.getBody().mensaje());
        verify(reporteServicio).cambiarEstado("123", estadoDTO);
    }
}