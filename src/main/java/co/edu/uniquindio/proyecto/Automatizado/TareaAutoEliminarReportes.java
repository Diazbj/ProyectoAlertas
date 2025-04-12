package co.edu.uniquindio.proyecto.Automatizado;

import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TareaAutoEliminarReportes {

    @Autowired
    private ReporteRepo reporteRepo;

    @Scheduled(cron = "0 * * * * *")
    public void eliminarReportesRechazadosVencidos() {
        List<Reporte> reportes = reporteRepo.findByEstadoActual(EstadoReporte.RECHAZADO);

        for (Reporte reporte : reportes) {
            if (reporte.getFechaLimiteEdicion() != null &&
                    LocalDateTime.now().isAfter(reporte.getFechaLimiteEdicion())) {

                reporte.setEstadoActual(EstadoReporte.ELIMINADO);

                HistorialReporte historial = HistorialReporte.builder()
                        .estado(EstadoReporte.ELIMINADO)
                        .motivo("Cambio automático por vencimiento del plazo de edición")
                        .fecha(LocalDateTime.now())
                        .reporteId(reporte.getId())
                        .build();

                if (reporte.getHistorialReporte() == null) {
                    reporte.setHistorialReporte(new ArrayList<>());
                }

                reporte.getHistorialReporte().add(historial);
                reporteRepo.save(reporte);
            }
        }
    }
}
