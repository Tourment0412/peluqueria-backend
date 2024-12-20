package co.edu.uniquindio.peluqueria.dtos.appointmentdto;

import java.time.LocalDateTime;

public record CreateAppointmentDTO(
        LocalDateTime date,
        float price,
        String service,
        String idClient,
        String idWorker
) {
}
