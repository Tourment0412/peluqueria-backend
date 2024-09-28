package co.edu.uniquindio.peluqueria.model.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("accounts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductSaleDetail {
    @Id
    private String id;
    private int quantity;
    private float subtotal;
    private String idProducto;
}
