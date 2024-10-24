package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.appointmentdto.AppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.CreateAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.InfoAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.UpdateAppointmentDTO;
import co.edu.uniquindio.peluqueria.model.documents.Appointment;

import java.util.List;

public interface AppointmentService {

    String createAppointment(CreateAppointmentDTO appointment) throws Exception;
    String updateAppointment(UpdateAppointmentDTO appointment) throws Exception;
    String deleteAppointment(String appointmentId) throws Exception;
    InfoAppointmentDTO getInfoAppointment(String appointmentId) throws Exception;

    List<AppointmentDTO> getAllAppointments();
}
