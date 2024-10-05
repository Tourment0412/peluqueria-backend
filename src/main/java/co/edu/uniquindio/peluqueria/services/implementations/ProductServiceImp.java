package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.RegisterProductDTO;
import co.edu.uniquindio.peluqueria.model.documents.Product;
import co.edu.uniquindio.peluqueria.repositories.ProductRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String registerProduct(RegisterProductDTO registerProductDTO) throws Exception {

        if(registerProductDTO.productName()==null || registerProductDTO.quantity()==0 || registerProductDTO.unitPrice()==0.0){
            throw new Exception("Some values are null or empty");
        }

        //TODO I should use an Account Service to get the Type of account (I just can do this if its admin)

        if(exitsProduct(registerProductDTO.productName())){
            throw new Exception("Product already exists");
        }
        Product product = new Product();
        product.setName(registerProductDTO.productName());
        product.setQuantity(registerProductDTO.quantity());
        product.setUnitPrice(registerProductDTO.unitPrice());
        return productRepository.save(product).getId();
    }



    @Override
    public List<ProductItemDTO> getAllProducts() throws Exception {
        //This could change if we do the treatment with the DTO's (I have to do a map with a stream)
        List<Product> products = productRepository.findAll();
        return products.stream().map(e -> new ProductItemDTO(
                e.getId(),
                e.getName(),
                e.getQuantity(),
                e.getUnitPrice()
        )).collect(Collectors.toList());
    }




    private boolean exitsProduct(String productName) throws Exception {
        Optional <Product> product = productRepository.findByName(productName);
        return product.isPresent();
    }

    private Product getProduct(String productName) throws Exception {
        Optional <Product> product = productRepository.findByName(productName);
        if(product.isEmpty()){
            throw new Exception("Product not found");
        }
        return product.get();
    }
}
