package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.enums.Rol;
import co.edu.uniquindio.proyecto.vo.CodigoValidacion;
import com.mongodb.client.model.Collation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import co.edu.uniquindio.proyecto.enums.Ciudad;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection="usuarios")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Usuario {

    @Id
    private ObjectId id;

    private String nombre;
    private String telefono;
    private Ciudad ciudad;
    private Rol rol;
    private EstadoUsuario estado;
    private String direccion;
    private String email;
    private String password;
    private CodigoValidacion codigoValidacion;
    private LocalDateTime fechaCreacion;
}
