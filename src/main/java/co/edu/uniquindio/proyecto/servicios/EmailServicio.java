package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.notificaciones.EmailDTO;

public interface EmailServicio {

    void enviarCorreo(EmailDTO emailDTO);
}
