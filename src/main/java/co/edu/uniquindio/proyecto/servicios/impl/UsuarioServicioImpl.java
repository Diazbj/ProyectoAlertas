package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.usuarios.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioActivacionDTO;
import co.edu.uniquindio.proyecto.dto.usuarios.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private List<Usuario> usuarios = new ArrayList<>( List.of(
            new Usuario("12","Jordy", "3123146223", "Armenia", "Calle 10", "jordy@email.com", "1213",true),
            new Usuario("14","Diana", "312212153", "Salento", "Cra17-25", "diana@email.com", "1212",true)
    ));

    @Override
    public void crear(CrearUsuarioDTO cuenta) throws Exception {
        usuarios.add( new Usuario(UUID.randomUUID().toString(), cuenta.nombre(), cuenta.telefono(), cuenta.ciudad(), cuenta.direccion(), cuenta.email(), cuenta.password(),true ) );
    }

    @Override
    public void editar(EditarUsuarioDTO cuenta) throws Exception {
        Optional<Usuario> usuario = usuarios.stream().filter(u -> u.getId().equals(cuenta.id())).findFirst();
        if(usuario.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        Usuario usuarioEncontrado = usuario.get();
        usuarioEncontrado.setNombre(cuenta.nombre());
        usuarioEncontrado.setTelefono(cuenta.telefono());
        usuarioEncontrado.setCiudad(cuenta.ciudad());
        usuarioEncontrado.setDireccion(cuenta.direccion());

    }

    @Override
    public void eliminar(String id) throws Exception {
        Optional<Usuario> usuario = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
        if(usuario.isEmpty()){
            throw new Exception("El usuario no existe");
        }
        usuarios.remove(usuario.get());
    }

    @Override
    public UsuarioDTO obtener(String id) throws Exception {
        Optional<Usuario> usuario = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
        if(usuario.isEmpty()){
            throw new Exception("El usuario no existe");
        }
        Usuario usuarioEncotrado = usuario.get();
        return new UsuarioDTO(usuarioEncotrado.getId(), usuarioEncotrado.getNombre(), usuarioEncotrado.getEmail());
    }

    @Override
    public List<UsuarioDTO> listarTodos(String nombre, String ciudad) {
        // Crear algunos usuarios de prueba


        // Filtro por nombre (opcional)
        if (nombre != null && !nombre.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getNombre().equalsIgnoreCase(nombre))  // Acceso a campo 'nombre' del record
                    .collect(Collectors.toList());
        }

        // Filtro por ciudad (opcional)
        if (ciudad != null && !ciudad.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getCiudad().equalsIgnoreCase(ciudad))  // Acceso a campo 'ciudad' del record
                    .collect(Collectors.toList());
        }

        return usuarios.stream().map(u -> new UsuarioDTO(u.getId(), u.getNombre(), u.getEmail())).collect(Collectors.toList());
    }

    @Override
    public void enviarCodigoActivacion(UsuarioActivacionDTO usuarioActivacionDTO) {

    }
    //TODO implementar todos los métodos de la interfaz
}

