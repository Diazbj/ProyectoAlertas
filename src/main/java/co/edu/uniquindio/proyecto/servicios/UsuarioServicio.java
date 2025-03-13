package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioDTO;

import java.util.List;

public interface UsuarioServicio {

    void crear(CrearUsuarioDTO cuenta) throws Exception;


    void editar(EditarUsuarioDTO cuenta) throws Exception;


    void eliminar(String id) throws Exception;


    UsuarioDTO obtener(String id) throws Exception;



    List<UsuarioDTO> listarTodos(String nombre, String ciudad);

}
