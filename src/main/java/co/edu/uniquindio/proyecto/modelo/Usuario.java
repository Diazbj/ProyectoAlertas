package co.edu.uniquindio.proyecto.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Usuario {
    private String id;
    private String nombre;
    private String telefono;
    private String ciudad;
    private String direccion;
    private String email;
    private String password;
}
