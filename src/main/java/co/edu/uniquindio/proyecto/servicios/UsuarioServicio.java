package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioActivacionDTO;
import org.springframework.web.bind.annotation.*;


public interface UsuarioServicio {

    void crear( CrearUsuarioDTO cuenta) throws Exception;

    void enviarCodigoActivacion( UsuarioActivacionDTO usuarioActivacionDTO) throws Exception ;

   void activarCuenta( UsuarioActivacionDTO usuarioActivacionDTO) throws Exception;


    void editar( EditarUsuarioDTO cuenta) throws Exception;

    void cambiarPassword( String id) throws Exception ;




    void eliminar(@PathVariable String id) throws Exception;



    UsuarioDTO obtener(@PathVariable String id) throws Exception;

}
