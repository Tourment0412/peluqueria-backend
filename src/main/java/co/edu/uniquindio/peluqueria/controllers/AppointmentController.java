package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.dtos.appointmentdto.CreateAppointmentDTO;
import co.edu.uniquindio.peluqueria.dtos.appointmentdto.UpdateAppointmentDTO;
import co.edu.uniquindio.peluqueria.services.interfaces.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AppointmentController {
    AppointmentService appointmentService;

    @PostMapping("/create-appointment")
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) throws Exception {

        String message = appointmentService.createAppointment(createAppointmentDTO);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete-appointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable String id) throws Exception {
        String message = appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update-appointment")
    public ResponseEntity<String> updateAppointment(@Valid @RequestBody UpdateAppointmentDTO updateAppointmentDTO) throws Exception {
        String message = appointmentService.updateAppointment(updateAppointmentDTO);
        return ResponseEntity.ok(message);
    }
}
