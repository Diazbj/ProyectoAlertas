package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.comentarios.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentarios.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "reporteId", source = "id")  // Mapea el ID del reporte
    @Mapping(target = "mensaje", source = "mensaje")  // "mensaje" en DTO → "mensaje" en Comentario
    @Mapping(target = "nombreUsuario", source = "nombreUsuario")
    Comentario toEntity(CrearComentarioDTO comentarioDTO);
}