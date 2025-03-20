package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioActivacionDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.proyecto.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.enums.Rol;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import co.edu.uniquindio.proyecto.vo.CodigoValidacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private final UsuarioRepo usuarioRepo;

    @Override
    public void crear(CrearUsuarioDTO crearUsuarioDTO) throws Exception {
        
        if(existeEmail(crearUsuarioDTO.email())) throw new Exception("El email ya existe");
        
        Usuario usuario = new Usuario();
        // Datos enviados por el usuario
        usuario.setNombre(crearUsuarioDTO.nombre());
        usuario.setPassword(crearUsuarioDTO.password());
        usuario.setEmail(crearUsuarioDTO.email());
        usuario.setTelefono(crearUsuarioDTO.telefono());
        usuario.setDireccion( crearUsuarioDTO.direccion());
        // Datos internos de la base de datos
        
        usuario.setRol(Rol.CLIENTE);
        usuario.setEstado(EstadoUsuario.INACTIVO);
        usuario.setFechaCreacion(LocalDateTime.now());
        
        //Se crea un codigo de validacion
        CodigoValidacion codigo= new CodigoValidacion(
                LocalDateTime.now(),
                generarCodigoAleatorio()
                
        );
        
        usuario.setCodigoValidacion(codigo);
        usuarioRepo.save(usuario);

    }

    private boolean existeEmail(String email) {
        return usuarioRepo.existsByEmail(email);
    }

    private String generarCodigoAleatorio(){
        String digitos="0123456789";
        StringBuilder codigo=new StringBuilder(digitos);
        for(int i=0;i<4;i++){
            int indice=(int) (Math.random()*digitos.length());
            codigo.append(digitos.charAt(indice));
        }
        return codigo.toString();
    }

    @Override 
    public void enviarCodigoActivacion(UsuarioActivacionDTO usuarioActivacionDTO) throws Exception {

    }

    @Override
    public void activarCuenta(UsuarioActivacionDTO usuarioActivacionDTO) throws Exception {

    }

    @Override
    public void editar(EditarUsuarioDTO cuenta) throws Exception {

    }

    @Override
    public void cambiarPassword(String id) throws Exception {

    }

    @Override
    public void eliminar(String id) throws Exception {

    }

    @Override
    public UsuarioDTO obtener(String id) throws Exception {
        return null;
    }
}
