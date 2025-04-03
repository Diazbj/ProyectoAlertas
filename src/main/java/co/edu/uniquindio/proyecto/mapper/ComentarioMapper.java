package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "nombreUsuario", ignore = true) // Se obtiene del token
    @Mapping(target = "clienteId", ignore = true) // Se obtiene del token
    @Mapping(target = "reporteId", ignore = true) // Se asigna manualmente
    Comentario toDocument(CrearComentarioDTO crearComentarioDTO);

    ComentarioDTO toDTO(Comentario comentario);
}