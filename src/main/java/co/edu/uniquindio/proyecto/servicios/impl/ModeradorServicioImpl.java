package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.moderadores.InformeDTO;
import co.edu.uniquindio.proyecto.dto.reportes.HistorialReporteDTO;
import co.edu.uniquindio.proyecto.dto.reportes.ReporteDTO;
import co.edu.uniquindio.proyecto.excepciones.CategoriaNoEncontradaException;
import co.edu.uniquindio.proyecto.excepciones.DatoRepetidoException;
import co.edu.uniquindio.proyecto.excepciones.EmailRepetidoException;
import co.edu.uniquindio.proyecto.excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.proyecto.mapper.CategoriaMapper;
import co.edu.uniquindio.proyecto.mapper.HistorialReporteMapper;
import co.edu.uniquindio.proyecto.mapper.InformeMapper;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.HistorialRepo;
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
    private final HistorialRepo historialRepo;
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
        List<Categoria> categorias = categoriaRepo.findAll();

        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void editarCategoria(String id, CategoriaDTO categoriaDTO) throws Exception {

        ObjectId objectId = new ObjectId(id);
        Optional<Categoria> categoriaOptional = categoriaRepo.findById(objectId);

        if(categoriaOptional.isEmpty()){
            throw new CategoriaNoEncontradaException("No se encontró la categoria con el id "+id);
        }

        Categoria categoria = categoriaOptional.get();
        categoriaMapper.toDocument(categoriaDTO,categoria);
        categoriaRepo.save(categoria);
    }

    @Override
    public void eliminarCategoria(String id) throws Exception {

        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new CategoriaNoEncontradaException("No se encontró la categoria con el id "+id);
        }

        //Buscamos el usuario que se quiere obtener
        ObjectId objectId = new ObjectId(id);

        Optional<Categoria> categoriaOptional = categoriaRepo.findById(objectId);

        //Si no se encontró el usuario, lanzamos una excepción
        if(categoriaOptional.isEmpty()){
            throw new CategoriaNoEncontradaException("No se encontró la categoria con el id "+id);
        }

        //Obtenemos el usuario que se quiere eliminar y le asignamos el estado eliminado
        Categoria categoria = categoriaOptional.get();
        categoriaRepo.delete(categoria);
    }

    @Override
    public List<InformeDTO> generarInforme(String ciudad, String categoria, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        Date fechaInicioDate = java.sql.Timestamp.valueOf(fechaInicio.atStartOfDay());
        Date fechaFinDate = java.sql.Timestamp.valueOf(fechaFin.atStartOfDay());

        // Llamar al repositorio que ya devuelve los conteos agrupados
        List<InformeDTO> reportes = informeRepo.findReportesByCiudadAndFecha(ciudad,categoria, fechaInicioDate, fechaFinDate);

        return reportes;
    }

    @Override
    public List<HistorialReporteDTO> obtenerHistorial(String id) throws Exception{

        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new UsuarioNoEncontradoException("No se encontró el reporte con el id "+id);
        }

        return historialRepo.obtenerHistorial(new ObjectId(id));
    }

}