package co.edu.uniquindio.peluqueria.model.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("productSales")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductSale {
    @Id
    private String id;
    private float total;
    private LocalDateTime date;
    private List<ProductSaleDetail> ProductSaleDetailList;
    private String idCliente;
    private String idWorker;
}
