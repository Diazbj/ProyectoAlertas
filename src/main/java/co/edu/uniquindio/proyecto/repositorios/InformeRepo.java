package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.moderadores.InformeDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface InformeRepo extends MongoRepository<Reporte, ObjectId> {

    @Aggregation(pipeline = {
            "{ $match: { ciudad: ?0, fechaCreacion: { $gte: ?1, $lte: ?2 } } }",
            "{ $lookup: { from: 'categorias', localField: 'CategoriaId', foreignField: '_id', as: 'categoriaInfo' } }",
            "{ $unwind: '$categoriaInfo' }",
            "{ $group: { _id: { ciudad: '$ciudad', categoria: '$categoriaInfo.nombre' }, cantidad: { $sum: 1 } } }",
            "{ $project: { _id: 0, ciudad: '$_id.ciudad', categoria: '$_id.categoria', cantidad: 1 } }"
    })
    List<InformeDTO> findReportesByCiudadAndFecha(String ciudad,String categoria, Date fechaInicio, Date fechaFin);
}



