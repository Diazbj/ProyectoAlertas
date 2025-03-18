package co.edu.uniquindio.proyecto.modelo;

import com.mongodb.client.model.Collation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="usuarios")
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
    private boolean activo;
}
