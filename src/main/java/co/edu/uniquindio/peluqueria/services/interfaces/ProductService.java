package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.RegisterProductDTO;

import java.util.List;

public interface ProductService {

    String registerProduct(RegisterProductDTO registerProductDTO) throws Exception;

    List<ProductItemDTO> getAllProducts() throws Exception;

    String updateProduct(ProductItemDTO productItemDTO) throws Exception;

    String deleteProduct(String id) throws Exception;
}
