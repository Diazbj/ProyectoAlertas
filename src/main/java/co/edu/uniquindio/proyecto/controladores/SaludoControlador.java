package co.edu.uniquindio.proyecto.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class SaludoControlador {
    @GetMapping()
    public String saludo() {
        return "Hola bienvenidos a esta maravillosa pagina";
    }

    @GetMapping("/{nombre}")
    public String saludoNombre(@PathVariable String nombre) {
        return "hola"+nombre;
    }
}
