package co.edu.uniquindio.peluqueria.model.vo;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductSaleDetail {
    @Id
    private String id;
    private int quantity;
    private float subtotal;
    private String idProduct;
}
