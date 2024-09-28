package co.edu.uniquindio.peluqueria.services;

import co.edu.uniquindio.peluqueria.repositories.ProductSaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSaleService {

    private final ProductSaleRepository productSaleRepository;

    public ProductSaleService(ProductSaleRepository productSaleRepository) {
        this.productSaleRepository = productSaleRepository;
    }

}
