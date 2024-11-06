package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.productdto.ProductFilterDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.RegisterProductDTO;
import co.edu.uniquindio.peluqueria.model.documents.Product;

import java.util.List;

public interface ProductService {

    String registerProduct(RegisterProductDTO registerProductDTO) throws Exception;

    List<ProductItemDTO> getAllProducts() throws Exception;

    String updateProduct(ProductItemDTO productItemDTO) throws Exception;

    String deleteProduct(String id) throws Exception;

    List<ProductItemDTO> filterProducts(ProductFilterDTO productFilterDTO) throws Exception;

    Product findProductById(String id) throws Exception;

    ProductItemDTO castProductItemDTO(Product product) throws Exception;

}
