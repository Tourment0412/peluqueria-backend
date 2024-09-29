package co.edu.uniquindio.peluqueria.model.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
    @Id
    private String id;
    private String name;
    private int quantity;
    private float unitPrice;
}
