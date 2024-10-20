package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.appointmentdto.CreateAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.InfoAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.UpdateAppointmentDTO;
import co.edu.uniquindio.peluqueria.model.documents.Appointment;
import co.edu.uniquindio.peluqueria.repositories.AppointmentRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import co.edu.uniquindio.peluqueria.services.interfaces.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private AccountService accountService;

    public AppointmentServiceImp(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public String createAppointment(CreateAppointmentDTO createAppointmentDTO) throws Exception {
        Appointment appointment = new Appointment();
        appointment.setDate(createAppointmentDTO.date());
        appointment.setPrice(createAppointmentDTO.price());
        //if(accountService.getAccountByDni(createAppointmentDTO.idClient())==null || accountService.getAccountByDni(createAppointmentDTO.idWorker())==null){
            //throw new Exception("The customer or employee is not registered");
        //}
        appointment.setIdClient(createAppointmentDTO.idClient());
        appointment.setIdWorker(createAppointmentDTO.idWorker());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    @Override
    public String updateAppointment(UpdateAppointmentDTO appointment) throws Exception {
        Appointment updateAppointment = getAppointment(appointment.appointmentId());
        updateAppointment.setDate(appointment.date());
        return updateAppointment.getId();
    }

    @Override
    public String deleteAppointment(String appointmentId) throws Exception {
        Appointment deletedAppointment = getAppointment(appointmentId);
        appointmentRepository.delete(deletedAppointment);
        return deletedAppointment.getId();
    }

    @Override
    public InfoAppointmentDTO getInfoAppointment(String appointmentId) throws Exception {
        Appointment appointment = getAppointment(appointmentId);
        return new InfoAppointmentDTO(
                appointment.getId(),
                appointment.getDate(),
                appointment.getPrice(),
                appointment.getService(),
                appointment.getIdClient(),
                appointment.getIdWorker()
        );
    }

    private Appointment getAppointment(String appointmentId) throws Exception {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new Exception("Unregistered appointment");
        }
        return appointmentRepository.findById(appointmentId).get();

    }
}
