package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.repositories.ProductSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSaleServiceImp {

    private final ProductSaleRepository productSaleRepository;

    public ProductSaleServiceImp(ProductSaleRepository productSaleRepository) {
        this.productSaleRepository = productSaleRepository;
    }

}
