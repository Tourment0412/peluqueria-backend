package co.edu.uniquindio.peluqueria.model.documents;

import co.edu.uniquindio.peluqueria.model.vo.ProductSaleDetail;
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
    private List<ProductSaleDetail> productSaleDetailList;
    private String idClient;
    //I don't think here should be
    //private String idWorker;

    @Builder
    public ProductSale(float total, LocalDateTime date, List<ProductSaleDetail> productSaleDetailList,String idClient) {
        this.total = total;
        this.date = date;
        this.productSaleDetailList = productSaleDetailList;
        this.idClient = idClient;
    }
}
