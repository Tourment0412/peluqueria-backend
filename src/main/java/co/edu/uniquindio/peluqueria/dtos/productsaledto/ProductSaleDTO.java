package co.edu.uniquindio.peluqueria.dtos.productsaledto;

import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;

import java.util.List;

public record ProductSaleDTO(
        String clientEmail,
        List<ProductItemDTO> products
) {
}
