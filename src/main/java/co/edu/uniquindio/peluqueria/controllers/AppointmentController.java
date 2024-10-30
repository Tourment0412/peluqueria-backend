package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.dtos.appointmentdto.AppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.CreateAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.UpdateAppointmentDTO;
import co.edu.uniquindio.peluqueria.model.documents.Appointment;
import co.edu.uniquindio.peluqueria.services.interfaces.AppointmentService;
import co.edu.uniquindio.peluqueria.services.interfaces.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;


    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) throws Exception {
        String message = appointmentService.createAppointment(createAppointmentDTO);
        appointmentService.informAppointment(createAppointmentDTO);
        System.out.println(message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable String id) throws Exception {
        String message = appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAppointment(@Valid @RequestBody UpdateAppointmentDTO updateAppointmentDTO) throws Exception {
        String message = appointmentService.updateAppointment(updateAppointmentDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        System.out.println("Fetching all appointments...");
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        System.out.println("Appointments fetched: " + appointments.size());
        return ResponseEntity.ok(appointments);
    }

}
