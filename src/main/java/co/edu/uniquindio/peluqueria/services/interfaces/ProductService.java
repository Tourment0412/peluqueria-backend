package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dto.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dto.productdto.RegisterProductDTO;
import co.edu.uniquindio.peluqueria.model.documents.Product;

import java.util.List;

public interface ProductService {

    String registerProduct(RegisterProductDTO registerProductDTO) throws Exception;
    List<ProductItemDTO> getAllProducts() throws Exception;
}
