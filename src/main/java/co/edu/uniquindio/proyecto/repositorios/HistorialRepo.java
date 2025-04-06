package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistorialRepo extends MongoRepository<HistorialReporte, String> {
}