package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, String> {

    Optional<Reporte> findByUbicacion_LatitudAndUbicacion_LongitudAndDescripcion(double latitud, double longitud, String descripcion);

    List<Reporte> findByUsuarioId(ObjectId usuarioId);

    List<Reporte> findTop10ByOrderByContadorImportanteDesc();

    @Query("{ 'ubicacion': { $geoWithin: { $centerSphere: [ [?0, ?1], ?2 ] } } }" )
    List<Reporte> findByUbicacionCerca(double latitud, double longitud, double radioEnRadianes);

    List<Reporte> findByEstadoActual(EstadoReporte estado);

}
