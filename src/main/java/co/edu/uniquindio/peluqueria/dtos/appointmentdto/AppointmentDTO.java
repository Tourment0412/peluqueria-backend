package co.edu.uniquindio.peluqueria.dtos.appointmentdto;

import java.time.LocalDateTime;

public record AppointmentDTO(String id,
                             LocalDateTime date,
                             String service,
                             float price,
                             String employeeName) {
}
