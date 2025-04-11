package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.InformeDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.excepciones.DatoRepetidoException;
import co.edu.uniquindio.proyecto.excepciones.EmailRepetidoException;
import co.edu.uniquindio.proyecto.excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.proyecto.mapper.CategoriaMapper;
import co.edu.uniquindio.proyecto.mapper.InformeMapper;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.InformeRepo;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepo categoriaRepo;
    private final ReporteMapper reporteMapper;
    private final InformeRepo informeRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void crearCategoria(CategoriaDTO categoriaDTO) throws Exception {
        if(this.existeCategoria(categoriaDTO.nombre()))
            throw new DatoRepetidoException("La categoria ya existe");


        Categoria categoria = categoriaMapper.toDocument(categoriaDTO);
        categoriaRepo.save(categoria);
    }

    public boolean existeCategoria(String nombre) {
        return categoriaRepo.existsByNombre(nombre);
    }


    @Override
    public List<CategoriaDTO> obtenerCategorias() throws Exception {
        return null;
    }

    @Override
    public void editarCategoria(String id, CategoriaDTO categoriaDTO) throws Exception {

    }

    @Override
    public void eliminarCategoria(String id) throws Exception {

    }

    @Override
    public List<InformeDTO> generarInforme(String ciudad, String categoria, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        Date fechaInicioDate = java.sql.Timestamp.valueOf(fechaInicio.atStartOfDay());
        Date fechaFinDate = java.sql.Timestamp.valueOf(fechaFin.atStartOfDay());

        // Llamar al repositorio que ya devuelve los conteos agrupados
        List<InformeDTO> reportes = informeRepo.findReportesByCiudadAndFecha(ciudad,categoria, fechaInicioDate, fechaFinDate);


        return reportes;
    }

}
