package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.productsaledto.ProductSaleDTO;

public interface ProductSaleService {

    String createProductSale (ProductSaleDTO productSaleDTO) throws Exception;
}
