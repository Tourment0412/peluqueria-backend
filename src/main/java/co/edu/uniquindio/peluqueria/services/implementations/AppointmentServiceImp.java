package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.accountdto.EmailDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.InfoAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.AppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.CreateAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.InfoAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.UpdateAppointmentDTO;
import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.documents.Appointment;
import co.edu.uniquindio.peluqueria.repositories.AppointmentRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import co.edu.uniquindio.peluqueria.services.interfaces.AppointmentService;
import co.edu.uniquindio.peluqueria.services.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppointmentServiceImp implements AppointmentService {

    private final EmailService emailService;

    private final AppointmentRepository appointmentRepository;
    private final AccountService accountService;

    public AppointmentServiceImp(AppointmentRepository appointmentRepository, AccountService accountServiceImp, AccountService accountService,EmailService emailService) {
        this.appointmentRepository = appointmentRepository;

        this.accountService = accountService;

        this.emailService = emailService;

    }

    @Override
    public String createAppointment(CreateAppointmentDTO createAppointmentDTO) throws Exception {
        Appointment appointment = new Appointment();
        appointment.setDate(createAppointmentDTO.date());
        appointment.setPrice(createAppointmentDTO.price());
        appointment.setService(createAppointmentDTO.service());

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

    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void informAppointment(CreateAppointmentDTO createAppointmentDTO) {
        try {
            InfoAccountDTO client=
                accountService.findAccountByDni(createAppointmentDTO.idClient());
            String subject="Cita realizada";
            String body="Se registro la cita con exito\n\n";
            body+="Fecha: "+createAppointmentDTO.date().toLocalDate()+"\n";
            body+="Precio: "+createAppointmentDTO.price()+"\n";
            body+="Servicio: "+createAppointmentDTO.service()+"\n\n\n";
            body+="Si hubo algun error o no esperaba este mensaje por favor " +
                    "comuniquece con nosotros";
            String receiver=client.email();
            emailService.sendEmail(new EmailDTO(
                    subject,
                    body,
                    receiver
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        // Obtener el nombre del trabajador basado en el idWorker
        String employeeName = accountService.getWorkerNameById(appointment.getIdWorker());

        // Crear y retornar el AppointmentDTO usando el record
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getDate(),
                appointment.getService(),
                appointment.getPrice(),
                employeeName
        );
    }
}
