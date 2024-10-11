package co.edu.uniquindio.peluqueria.dtos.appointmentdto;

import java.time.LocalDateTime;

public record UpdateAppointmentDTO(
        String appointmentId,
        LocalDateTime date
) {
}
