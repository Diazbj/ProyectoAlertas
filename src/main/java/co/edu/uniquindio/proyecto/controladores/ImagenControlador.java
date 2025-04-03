package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.servicios.ImagenServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imagenes")
public class ImagenControlador {

    private final ImagenServicio imagenServicio;

    @PostMapping
    public String subir(@RequestParam("image")MultipartFile imagen) throws Exception{
        Map mapa = imagenServicio.subirImagen(imagen);
        return mapa.toString();
    }
}
