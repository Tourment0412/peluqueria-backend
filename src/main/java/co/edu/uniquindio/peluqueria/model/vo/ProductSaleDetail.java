package co.edu.uniquindio.peluqueria.model.vo;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductSaleDetail {

    private String idProduct;
    private String productName;
    private int quantity;
    private float subtotal;

    @Builder
    public ProductSaleDetail(String idProduct, String productName, int quantity, float subtotal) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

}
