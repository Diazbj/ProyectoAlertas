package co.edu.uniquindio.proyecto.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
@Setter
@Getter
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;

    private String nombre;
    private String telefono;
    private String ciudad;
    private String direccion;
    private String email;
    private String password;
}
