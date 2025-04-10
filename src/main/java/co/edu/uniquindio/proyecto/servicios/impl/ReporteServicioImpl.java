package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.notificaciones.NotificacionDTO;
import co.edu.uniquindio.proyecto.dto.reportes.*;
import co.edu.uniquindio.proyecto.excepciones.*;
import co.edu.uniquindio.proyecto.mapper.ComentarioMapper;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final CategoriaRepo categoriaRepo;
    private final ComentarioMapper comentarioMapper;

    @Override
    public void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception {

        String id = usuarioServicio.obtenerIdSesion();

        // Mapear DTO a documento y guardar en la base de datos
        Reporte reporte = reporteMapper.toDocument(crearReporteDTO);
        reporte.setUsuarioId(new ObjectId(id));
        // Buscar al usuario por su ID
        Usuario usuario = usuarioRepo.findById(new ObjectId(id)).orElseThrow(() -> new Exception("Usuario no encontrado"));
        // Establecer el nombre del usuario en el reporte
        reporte.setNombreUsuario(usuario.getNombre());  // Aquí se asigna el nombre del usuario

        // Validar si el categoriaId es válido antes de intentar convertirlo
        if (crearReporteDTO.categoriaId() == null || !ObjectId.isValid(crearReporteDTO.categoriaId())) {
            throw new CategoriaNoEncontradaException("Categoría no encontrada");
        }

        // Validar que la categoría existe antes de asignarla al reporte
        ObjectId categoriaId = new ObjectId(crearReporteDTO.categoriaId());
        Categoria categoria = categoriaRepo.findById(categoriaId).orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada"));

        // Asignar la categoría al reporte
        reporte.setCategoriaId(categoria.getId());

        reporteRepo.save(reporte);
        NotificacionDTO notificacionDTO = new NotificacionDTO(
                "Nuevo Reporte",
                "Se acaba de crear un nuevo reporte: " + reporte.getTitulo(),
                "reports"
        );


        webSocketNotificationService.notificarClientes(notificacionDTO);
    }

    @Override
    public List<ReporteDTO> obtenerReportes() {
        // Obtenemos todos los reportes
        List<Reporte> reportes = reporteRepo.findAll();

        // Filtramos los que NO están eliminados ni resueltos
        return reportes.stream()
                .filter(reporte ->
                        reporte.getEstadoActual() != EstadoReporte.ELIMINADO && reporte.getEstadoActual() != EstadoReporte.RESUELTO
                )
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteDTO> obtenerReportesUsuario() throws Exception {

        String id = usuarioServicio.obtenerIdSesion();
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new UsuarioNoEncontradoException("No se encontró el usuario con el id " + id);
        }

        //Buscamos el usuario que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(objectId);

        //Si no se encontró el usuario, lanzamos una excepción
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNoEncontradoException("No se encontró el usuario con el id " + id);
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
        List<Reporte> topReportes = reporteRepo.findTop10ByOrderByContadorImportanteDesc();

        //Convertir a DTO usando mapper
        return topReportes.stream()
                .filter(reporte ->
                        reporte.getEstadoActual() != EstadoReporte.ELIMINADO && reporte.getEstadoActual() != EstadoReporte.RESUELTO
                )
                .map(reporteMapper::toDTO)
                .toList();
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
            throw new ReporteNoEncontradoException("No se encontró el reporte con el id " + id);
        }

        // Buscamos el reporte que se quiere obtener usando el id en formato String
        Optional<Reporte> reporteOptional = reporteRepo.findById(id);

        // Si no se encontró el reporte, lanzamos una excepción
        if (reporteOptional.isEmpty()) {
            throw new ReporteNoEncontradoException("No se encontró el reporte con el id " + id);
        }

        Reporte reporte = reporteOptional.get();

        // Retornamos el reporte encontrado convertido a DTO
        return reporteMapper.toDTO(reporteOptional.get());
    }


    @Override
    public int marcarImportante(String id) throws Exception {

        Optional<Reporte> optionalReporte = reporteRepo.findById(id);

        if (optionalReporte.isEmpty()) {
            throw new ReporteNoEncontradoException("No existe el reporte con id: " + id);
        }

        Reporte reporte = optionalReporte.get();
        String usuarioIdString = usuarioServicio.obtenerIdSesion();
        ObjectId usuarioId = new ObjectId(usuarioIdString);
        List<ObjectId> listaUsuarios = reporte.getContadorImportante();

        if (listaUsuarios == null) {
            listaUsuarios = new ArrayList<>();
            listaUsuarios.add(usuarioId);
            reporte.setContadorImportante(listaUsuarios);
            reporteRepo.save(reporte);
            return 1;
        }

        if (!listaUsuarios.contains(usuarioId)) {

            listaUsuarios.add(usuarioId);
            reporte.setContadorImportante(listaUsuarios);
            reporteRepo.save(reporte);
        }

        return listaUsuarios.size();
    }

    @Override
    public void cambiarEstado(String id, EstadoReporteDTO estadoDTO) throws Exception {
        Optional<Reporte> optionalReporte = reporteRepo.findById(id);

        if (optionalReporte.isEmpty()) {
            throw new ReporteNoEncontradoException("El reporte no existe");
        }

        Reporte reporte = optionalReporte.get();

        // Obtener ID y ROL del usuario autenticado
        String idUsuario = usuarioServicio.obtenerIdSesion();
        String rolUsuario = usuarioServicio.obtenerRolSesion();

        boolean esCreador = reporte.getUsuarioId().toHexString().equals(idUsuario);
        boolean esModerador = rolUsuario.equals("MODERADOR");

        if (!esCreador && !esModerador) {
            throw new AccesoNoPermitidoException("No tienes permiso para cambiar el estado de este reporte");
        }

        EstadoReporte nuevoEstado = EstadoReporte.valueOf(estadoDTO.nuevoEstado().toUpperCase());

        // Validación si el nuevo estado es RECHAZADO
        if (nuevoEstado == EstadoReporte.RECHAZADO) {
            if (!esModerador) {
                throw new AccesoNoPermitidoException("Solo un moderador puede rechazar un reporte");
            }
            if (estadoDTO.motivo() == null || estadoDTO.motivo().isBlank()) {
                throw new Exception("Debes proporcionar un motivo al rechazar el reporte");
            }

            // Establecer fecha límite de edición (5 días después del rechazo)
            LocalDateTime fechaLimite = LocalDateTime.now().plusMinutes(1);
            reporte.setFechaLimiteEdicion(fechaLimite);
        } else {
            // Limpiar fecha límite si no fue rechazado
            reporte.setFechaLimiteEdicion(null);
        }

        // Actualizar estado actual del reporte
        reporte.setEstadoActual(nuevoEstado);
        reporteRepo.save(reporte);

        // Crear historial del cambio
        HistorialReporte.HistorialReporteBuilder historialBuilder = HistorialReporte.builder()
                .estado(nuevoEstado)
                .motivo(estadoDTO.motivo())
                .fecha(LocalDateTime.now())
                .reporteId(reporte.getId());

// Incluir fecha límite si el estado es RECHAZADO
        if (nuevoEstado == EstadoReporte.RECHAZADO) {
            historialBuilder.fechaLimiteEdicion(reporte.getFechaLimiteEdicion());
        }

// Construir historial
        HistorialReporte historial = historialBuilder.build();

// Añadir historial al reporte
        if (reporte.getHistorialReporte() == null) {
            reporte.setHistorialReporte(new ArrayList<>());
        }

        reporte.getHistorialReporte().add(historial);

// Guardar el reporte con el historial actualizado
        reporteRepo.save(reporte);

    }

}

