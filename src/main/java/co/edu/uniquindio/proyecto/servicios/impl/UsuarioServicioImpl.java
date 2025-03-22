package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioActivacionDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.proyecto.excepciones.EmailRepetidoException;
import co.edu.uniquindio.proyecto.mapper.UsuarioMapper;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.enums.Rol;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import co.edu.uniquindio.proyecto.modelo.vo.CodigoValidacion;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private final UsuarioRepo usuarioRepo;

    private final UsuarioMapper usuarioMapper;

    @Override
    public void crear(CrearUsuarioDTO crearUsuarioDTO) throws Exception {
        
        if(existeEmail(crearUsuarioDTO.email())) throw new EmailRepetidoException("El email ya existe");


        Usuario usuario = usuarioMapper.toDocument(crearUsuarioDTO);
        usuarioRepo.save(usuario);
        
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
        StringBuilder codigo=new StringBuilder();
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

        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new Exception("No se encontró el usuario con el id "+id);
        }

        //Buscamos el usuario que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(objectId);

        //Si no se encontró el usuario, lanzamos una excepción
        if(usuarioOptional.isEmpty()){
            throw new Exception("No se encontró el usuario con el id "+id);
        }

        //Retornamos el usuario encontrado convertido a DTO
        return usuarioMapper.toDTO(usuarioOptional.get());

    }
}
