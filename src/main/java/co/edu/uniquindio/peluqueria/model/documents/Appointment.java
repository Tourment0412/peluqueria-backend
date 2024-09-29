package co.edu.uniquindio.peluqueria.model.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("appointments")
@Getter
@Setter
public class Appointment {
    @Id
    private String id;
    private LocalDateTime date;
    private float price;
    private String service;
    private String idClient;
    private String idWorker;
}
